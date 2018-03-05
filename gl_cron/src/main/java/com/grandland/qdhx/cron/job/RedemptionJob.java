package com.grandland.qdhx.cron.job;

import com.alibaba.druid.util.StringUtils;
import com.grandland.qdhx.core.domain.BudeadLine;
import com.grandland.qdhx.core.domain.BusinessMortgage;
import com.grandland.qdhx.core.domain.RedemptionApplication;
import com.grandland.qdhx.core.domain.RegulatoryBusiness;
import com.grandland.qdhx.core.mapper.*;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.ConstantUtil;
import com.grandland.qdhx.core.utils.DateUtil;
import com.grandland.qdhx.core.utils.PrimaryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 业务到期前检查是否赎货
 */
public class RedemptionJob extends BaseJob {
    private static final Logger LOG = LoggerFactory.getLogger(RedemptionJob.class);

    private static final Integer AHEAD_TIME_DAYS_DEFAULT = 5;

    private static final double EPSILON = 0.00001;

    private static final String MSG_FORMAT = "您应于%s填仓，金额%.2f万元";

    private Map<String, BudeadLine> redemptionMap = new HashMap<>();

    private Map<String, BusinessMortgage> businessMap = new HashMap<>();

    @Autowired
    private BusinessMortgageMapper mortgageMapper;

    @Autowired
    private RegulatoryBusinessMapper regulatoryBusinessMapper;

    @Autowired
    private BotRedemptionMapper botRedemptionMapper;

    @Autowired
    private BudeadLineMapper budeadLineMapper;

    @Autowired
    private BusinessFlowMapper flowMapper;


    @Override
    protected boolean isMatchCond(BusinessMortgage mortgage) {
        Date today = DateUtil.getStartOfToday();
        Date deadLineDay = DateUtil.getStartOfDay(mortgage.getDeadLine());
        //如果当前时间不小于业务到期时间，则跳过
        if (today.after(deadLineDay) || today.equals(deadLineDay)) {
            LOG.info("not redeem after dead line of business, {}", mortgage);
            return false;
        }

        //提前N天是否是今天，如果不是，跳过
        int days = AHEAD_TIME_DAYS_DEFAULT;
        if (!today.equals(DateUtil.getNDaysOffset(deadLineDay, -days))) {
            return false;
        }

        LOG.info("redemption {} days ahead of time, business:{}", days, mortgage);
        return isRedeem(mortgage);
    }

    /**
     * 比对赎货情况
     * @param mortgage
     * @return
     */
    private boolean isRedeem(BusinessMortgage mortgage) {
        //根据融资合同编号找到对应的第一笔业务信息
        String conum = getFirstConum(mortgage.getBusinessConum());
        BusinessMortgage business = mortgage;
        if (!conum.equals(mortgage.getBusinessConum())) {
            Example criteria = new Example();
            criteria.createCriteria().andFieldEqualTo("business_conum", criteria)
                    .andFieldEqualTo("coo_num", mortgage.getCooNum())
                    .andFieldEqualTo("borrower_name", mortgage.getBorrowerName());
            List<BusinessMortgage> list = mortgageMapper.selectByExample(mortgage, criteria);
            if (ObjectUtils.isEmpty(list)) {
                LOG.error("has no business by first conum:{}, please check! {}", conum, mortgage);
                return false;
            } else if (list.size() > 1) {
                LOG.warn("that has {} business by first conum:{}, please check! {}", list.size(), conum, mortgage);
            }
            business = list.get(0);
        }

        //通过港商平台的融资编号找到融资信息
        String financingId = business.getFinancingId();
        if (StringUtils.isEmpty(financingId)) {
            LOG.error("the financing id is empty, please check! {}", business);
            return false;
        }
        Example criteria = new Example();
        criteria.createCriteria().andFieldEqualTo("number_id", financingId);
        RegulatoryBusiness regulatoryBusiness = new RegulatoryBusiness();
        List<RegulatoryBusiness> list = regulatoryBusinessMapper.selectByExample(regulatoryBusiness, criteria);
        if (ObjectUtils.isEmpty(list)) {
            //TODO insert alarm
            LOG.error("that has no regulatory business: {}", mortgage);
            return false;
        } else if (list.size() > 1) {
            LOG.warn("that has {} regulatory business: {}", list.size(), mortgage);
        }
        regulatoryBusiness = list.get(0);
        Double amount = regulatoryBusiness.getFinancingAmount() * 10000;
        Double goods = regulatoryBusiness.getGoodsQuantity();

        //通过港商平台的融资编号找到赎货信息
        Double redeemAmount = 0.0;
        Double redeemGoods = 0.0;
        int redeemTimes = 0;
        criteria.clear().andFieldLike("number_id", financingId + "-");
        RedemptionApplication redemption = new RedemptionApplication();
        List<RedemptionApplication> redemptionList = botRedemptionMapper.selectByExample(redemption, criteria);
        if (!ObjectUtils.isEmpty(redemptionList)) {
            redeemTimes = redemptionList.size();
            for (RedemptionApplication redemp : redemptionList) {
                redeemAmount += redemp.getRepaidMoney();
                redeemGoods += redemp.getRedemptionQuantity();
            }
        }

        //计算未赎货信息
        Double notRedeemAmount = amount - redeemAmount;
        Double notRedeemGoods = goods - redeemGoods;

        //信息整理
        BudeadLine redemptionInfo = new BudeadLine();
        {
            redemptionInfo.setBusinessId(business.getId());
            redemptionInfo.setRizhaoId(business.getFinancingId());
            redemptionInfo.setShuhuoTimes(redeemTimes);
            redemptionInfo.setDeadLine(business.getDeadLine());
            redemptionInfo.setShuhuoValue(BigDecimal.valueOf(redeemAmount));
            redemptionInfo.setShuhuoCount(BigDecimal.valueOf(redeemGoods));
            redemptionInfo.setNotRedeemValue(BigDecimal.valueOf(notRedeemAmount));
            redemptionInfo.setNotRedeemCount(BigDecimal.valueOf(notRedeemGoods));
            redemptionInfo.setCreateTime(new Date());
        }
        if (Math.abs(redeemAmount) > -EPSILON && Math.abs(redeemAmount) < EPSILON) {
            //未赎货
            redemptionInfo.setIsShuhuo(0);
        } else if (notRedeemAmount > 0 && notRedeemGoods > 0) {
            //已赎部分货
            redemptionInfo.setIsShuhuo(1);
        } else if (notRedeemAmount <= 0 && notRedeemGoods <= 0) {
            //已赎全部货
            redemptionInfo.setIsShuhuo(2);
        }
        LOG.debug("redemptionInfo:{}", redemptionInfo);
        redemptionMap.put(business.getBusinessConum(), redemptionInfo);
        businessMap.put(business.getBusinessConum(), business);

        if (redemptionInfo.getIsShuhuo() == 2) {
            LOG.info("is redeem all of goods, conum:{}, {}", business.getBusinessConum(), mortgage);
            return false;
        }
        return true;
    }


    /**
     * 根据融资合同编号获取第一笔业务的融资合同编号
     * @param conum
     * @return
     */
    private String getFirstConum(String conum) {
        //新融资合同编号格式为第一笔业务融资合同编号_N
        String splitSymbol = ConstantUtil.SYMBOL_UNDERLINE;
        if (conum.contains(splitSymbol)) {
            String[] fields = conum.split(splitSymbol);
            if (fields.length > 2) {
                LOG.warn("connum[{}] has {} parts after split by {}", conum, fields.length, splitSymbol);
            }
            conum = fields[0];
        }
        return conum;
    }


    @Override
    protected void handler(BusinessMortgage mortgage) {
        String firstConum = getFirstConum(mortgage.getBusinessConum());
        if (redemptionMap.containsKey(firstConum)) {
            BudeadLine redemption = redemptionMap.get(firstConum);
            //操作数据库
            updateRedemption(redemption);
            //推送APP
            BusinessMortgage business = businessMap.get(firstConum);
            List<String> users = getUser(business);
            String msg = getRemindMsg(redemption, business.getDeadLine());
            for (String user : users) {
                pushMsgToApp(redemption.getId(), redemption.getBusinessId(), user, msg, business);
            }
        } else {
            LOG.warn("there is no info in map, conum:{}, business:{}", firstConum, mortgage);
        }
    }

    /**
     * 更新赎货信息
     * @param redemption
     */
    private void updateRedemption(BudeadLine redemption) {
        Example criteria = new Example();
        criteria.createCriteria().andFieldEqualTo(PrimaryKey.BUSINESS_ID, redemption.getBusinessId());
        List<BudeadLine> list = budeadLineMapper.selectByExample(redemption, criteria);
        try {
            if (ObjectUtils.isEmpty(list)) {
                budeadLineMapper.insert(redemption);
            } else {
                if (list.size() > 1) {
                    LOG.warn("there has {} message from dead line table, {}", list.size(), redemption);
                }
                redemption.setId(list.get(0).getId());
                budeadLineMapper.updateByPrimaryKeySelective(redemption);
            }
        } catch (Exception e) {
            LOG.error("exception occurs:{}", e);
        }
    }

    /**
     * 提示信息
     * @param redemption
     * @param date
     * @return
     */
    public String getRemindMsg(BudeadLine redemption, Date date) {
        BigDecimal notRedeemValue = redemption.getNotRedeemValue();
        Double amount = notRedeemValue.divide(new BigDecimal(10000), BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.format(MSG_FORMAT, DateUtil.format(date, "MM月dd日"), amount);
    }

    //TODO 获取提示信息发送人
    public List<String> getUser(BusinessMortgage mortgage) {
        List<String> userList = new ArrayList<>();
        //经营单位-客户经理
        userList.add(mortgage.getCustomerManager());
        //流程相关人员
        List<String> operators = flowMapper.findDistinctOperatorById(String.valueOf(mortgage.getId()));
        userList.addAll(operators);
        return userList;
    }
}
