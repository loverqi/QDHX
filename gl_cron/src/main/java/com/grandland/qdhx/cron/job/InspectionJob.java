package com.grandland.qdhx.cron.job;

import com.alibaba.druid.util.StringUtils;
import com.grandland.qdhx.core.domain.*;
import com.grandland.qdhx.core.manage.DictManage;
import com.grandland.qdhx.core.mapper.BsInspectionDetailMapper;
import com.grandland.qdhx.core.mapper.BsInspectionMapper;
import com.grandland.qdhx.core.mapper.GlInspectFreqMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.CodeCategory;
import com.grandland.qdhx.core.utils.ConstantUtil;
import com.grandland.qdhx.core.utils.DateUtil;
import com.grandland.qdhx.core.utils.PrimaryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 巡核库业务
 */
public class InspectionJob extends BaseJob {
    private static final Logger LOG = LoggerFactory.getLogger(InspectionJob.class);

    private static final Integer AHEAD_TIME_DAYS_DEFAULT = 5;

    private static final String MSG_FORMAT = "您应于%s巡核库，地点:%s";

    private Map<Integer, Map<GlInspectFreq, Date>> inspectMap = new HashMap<>();

    @Autowired
    private GlInspectFreqMapper inspectFreqMapper;

    @Autowired
    private DictManage dictmanage;

    @Autowired
    private BsInspectionMapper inspectionMapper;

    @Autowired
    private BsInspectionDetailMapper detailMapper;

    @Override
    protected boolean isMatchCond(BusinessMortgage mortgage) {
        //根据监管机构代码获取巡核频率
        List<GlInspectFreq> freqList = inspectFreqMapper.findBySuperCode(mortgage.getSuperDep(), false);
        if (ObjectUtils.isEmpty(freqList)) {
            LOG.error("no freq of super unit[{}], please check!!", mortgage.getSuperDep());
            return false;
        }

        boolean isMatch = false;
        Date today = DateUtil.getStartOfToday();
        for (GlInspectFreq freq : freqList) {
            Date startDay = DateUtil.getStartOfDay(mortgage.getCreateTime());
            //如果当前时间不大于业务开始时间，则跳过
            if (today.before(startDay) || today.equals(startDay)) {
                continue;
            }

            //根据时间单位获取字典中对应的时间符号
            GlSysCode sysCode = dictmanage.getSysCode(CodeCategory.Common.TIME_UNIT, freq.getUnit());
            if (!ObjectUtils.isEmpty(sysCode)) {
                //找到最近的巡核时间
                Date inspectDate = getClosestInspectDay(startDay, today, freq.getNum(), sysCode.getRemark());
                if (inspectDate == null) {
                    continue;
                }
                //提前N天是否是今天，如果不是，跳过
                int days = AHEAD_TIME_DAYS_DEFAULT;
                if (!today.equals(DateUtil.getNDaysOffset(inspectDate, -days))) {
                    continue;
                }

                LOG.info("inspect {} days ahead of time, business:{}, fre:{}", days, mortgage, freq);
                isMatch = true;
                if (!inspectMap.containsKey(mortgage.getId())) {
                    Map<GlInspectFreq, Date> tmpMap = new HashMap<>();
                    tmpMap.put(freq, inspectDate);
                    inspectMap.put(mortgage.getId(), tmpMap);
                } else {
                    Map<GlInspectFreq, Date> tmpMap = inspectMap.get(mortgage.getId());
                    if (!tmpMap.containsKey(freq)) {
                        tmpMap.put(freq, inspectDate);
                    }
                }
            } //end of if
        } // end of freq

        return isMatch;
    }


    /**
     * 获取距离现在最近的巡核日期
     * @param startDay
     * @param today
     * @param num
     * @param timeUnit
     * @return
     */
    private Date getClosestInspectDay(Date startDay, Date today, int num, String timeUnit) {
        Date inspectDay = startDay;
        while (today.after(inspectDay)) {
            inspectDay = DateUtil.getOffsetDay(inspectDay, num, timeUnit);
            if (inspectDay == null) {
                return inspectDay;
            }
        }
        return inspectDay;
    }

    @Override
    protected void handler(BusinessMortgage mortgage) {
        if (inspectMap.containsKey(mortgage.getId())) {
            Map<GlInspectFreq, Date> tmpMap = inspectMap.get(mortgage.getId());
            Iterator<Map.Entry<GlInspectFreq, Date>> itor = tmpMap.entrySet().iterator();
            while (itor.hasNext()) {
                Map.Entry<GlInspectFreq, Date> entry = itor.next();
                GlInspectFreq inspectFreq = entry.getKey();
                Date inspectDate = entry.getValue();
                //更新巡核库表
                updateInspection(mortgage);
                //更新巡核库详情表并推送APP
                List<Integer> users = getUserByDeptType(inspectFreq.getDeptType(), mortgage);
                updateDetail(mortgage, users, inspectDate);
            }
        } else {
            LOG.warn("there is not fre info in map, business:{}", mortgage);
        }
    }


    /**
     * 更新巡核库
     * @param mortgage
     * @return
     */
    public int updateInspection(BusinessMortgage mortgage) {
        int affectedNum = 0;
        BusinessInspection inspection = new BusinessInspection();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo(PrimaryKey.BUSINESS_ID, mortgage.getId());
        List<BusinessInspection> list = inspectionMapper.selectByExample(inspection, example);
        if (ObjectUtils.isEmpty(list)) {
            inspection.setBusinessId(mortgage.getId());
            inspection.setDeleted(false);
            inspection.setCreateTime(new Date());
            try {
                affectedNum = inspectionMapper.insert(inspection);
            } catch (Exception e) {
                LOG.error("exception occurs:{}", e);
            }
        } else {
            inspection = list.get(0);
            inspection.setUpdateTime(new Date());
            try {
                affectedNum = inspectionMapper.update(inspection);
            } catch (Exception e) {
                LOG.error("exception occurs:{}", e);
            }
        }
        return affectedNum;
    }


    public void updateDetail(BusinessMortgage mortgage, List<Integer> users, Date inspectDate) {
        for (Integer user : users) {
            BusinessInspectionDetail inspectionDetail = new BusinessInspectionDetail();
            Example criteria = new Example();
            criteria.createCriteria().andFieldEqualTo(PrimaryKey.BUSINESS_ID, mortgage.getId())
                    .andFieldEqualTo(PrimaryKey.USER_ID, user)
                    .andFieldEqualTo("check_time", DateUtil.formatDefault(inspectDate));
            List<BusinessInspectionDetail> list = detailMapper.selectByExample(inspectionDetail, criteria);
            if (ObjectUtils.isEmpty(list)) {
                inspectionDetail.setBusinessId(mortgage.getId());
                inspectionDetail.setUserId(user);
                inspectionDetail.setCheckTime(inspectDate);
                inspectionDetail.setChecked(false);
                inspectionDetail.setCreateTime(new Date());
                try {
                    detailMapper.insertDetail(inspectionDetail);
                } catch (Exception e) {
                    LOG.error("exception occurs:{}", e);
                }

                String msg = getRemindMsg(mortgage, inspectDate);
                pushMsgToApp(inspectionDetail.getId(), mortgage.getId(), String.valueOf(user), msg, mortgage);
            } else {
                LOG.warn("there has already inspection detail, businessId:{}, userId:{}, inspectDate:{}", mortgage.getId(), user, inspectDate);
            }
        }
    }


    public String getRemindMsg(BusinessMortgage mortgage, Date inspectDate) {
        //TODO 违法地点
        return String.format(MSG_FORMAT, DateUtil.format(inspectDate, "MM月dd日"), "违法地点");
    }


    public List<Integer> getUserByDeptType(String deptType, BusinessMortgage mortgage) {
        List<Integer> userList = new ArrayList<>();
        //供应链--现场检查岗
        if (ConstantUtil.STRING_ZERO_ONE.equals(deptType)) {
            String wareSuper = mortgage.getWareSuper();
            if (!StringUtils.isEmpty(wareSuper)) {
                userList.add(Integer.parseInt(wareSuper));
            } else {
                //业务中没有指定现场检查岗，需要根据岗位将人员查出
                //TODO
            }
        } else if (ConstantUtil.STRING_ZERO_TWO.equals(deptType)) {
            //经营单位-客户经理
            userList.add(Integer.parseInt(mortgage.getCustomerManager()));
        }
        return userList;
    }
}
