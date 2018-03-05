package com.grandland.qdhx.spider.common.processor;

import com.alibaba.fastjson.JSON;
import com.grandland.qdhx.core.domain.*;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;
import com.grandland.qdhx.core.utils.ConstantUtil;
import com.grandland.qdhx.core.utils.DateUtil;
import com.grandland.qdhx.core.utils.StringUtil;
import com.grandland.qdhx.core.utils.URLUtil;
import com.grandland.qdhx.spider.common.login.RizhaoPortLogin;
import com.grandland.qdhx.spider.common.processor.base.BasePageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;
import java.util.Map.Entry;

/**
 * 页面解析获取类，后期修改为模板方法的形式
 * @author loverqi
 * @date 2018年1月30日
 */
@Component
public class RizhaoPortPageProcessor extends BasePageProcessor {

    /** 用户名*/
    private static final String USERNAME = "1030012";
    /** 密码*/
    private static final String PASSWORD = "hxyh1234+";

    /** 域名*/
    public static final String HOST = "http://218.56.178.124:8086";

    /** 融资项目列表*/
    public final static String LIST_JSON_URL = HOST
            + "/rzbot/member/logistics/cargo/cargoManagerAction!queryMyCargo.action?json=true";
    /** 融资监管业务单*/
    private final static String REGULATORY_BUSINESS_URL = HOST
            + "/rzbot/member/logistics/cargo/cargoDetailAction!enterModSqd.action?cargoId=";
    /** 融资监管业务图*/
    private final static String BUSINESS_DIAGRAM_URL = HOST
            + "/rzbot/member/logistics/cargo/rzjgywAction!enterRzjgywt.action?id=";
    /** 港口关联*/
    private final static String PORT_ASSOCIATION = HOST
            + "/rzbot/member/logistics/remessage/remessageAction!enterGkRemessge.action?";

    /** 模拟登录获取cookie*/
    @Autowired
    private RizhaoPortLogin rizhaoPortLogin;

    /*
     * 设置登录认证信息的cookie
     * @see us.codecraft.webmagic.processor.PageProcessor#getSite()
     */
    @Override
    public Site getSite() {
        Site site = super.getSite();

        //获得cookies
        Map<String, String> cookies = rizhaoPortLogin.getLoginSession(USERNAME, PASSWORD);

        //设置cookies
        Set<Entry<String, String>> entrySet = cookies.entrySet();
        for (Entry<String, String> entry : entrySet) {
            site.addCookie(entry.getKey(), entry.getValue());
        }

        return site;
    }

    /*
     * 页面抽取逻辑
     * @see us.codecraft.webmagic.processor.PageProcessor#process(us.codecraft.webmagic.Page)
     */
    @Override
    public void process(Page page) {

        //获取到当前页面的url
        String pathString = page.getUrl().toString();
        if (pathString.startsWith(LIST_JSON_URL)) { //列表页具体的操作
            //从列表页抽取接下来需要爬取的页面
            putNextPage(page);
        } else if (pathString.startsWith(REGULATORY_BUSINESS_URL)) { //融资监管业务单
            //融资监管业务单页面解析
            MyBatisPojo obj = getRegulatoryBusinessUrlDate(page);
            putBeanToPage(page, obj);

            List<RedemptionApplication> objs = getRedemptionApplicationFormDate(page);
            putBeanToPage(page, objs);

        } else if (pathString.startsWith(BUSINESS_DIAGRAM_URL)) {//融资监管业务图
            //解析融资监管业务图页面解析
            List<RegulatoryTimeState> businessDiagramDate = getBusinessDiagramDate(page);

            //将需要储存的数据放入结果并交给保存器处理
            putBeanToPage(page, businessDiagramDate);
        } else if (pathString.startsWith(PORT_ASSOCIATION)) {//岗位关联

            //港口关联时间状态图
            List<PortAssociationTimeState> pasts = getPortAssociationTimeStateDate(page);
            putBeanToPage(page, pasts);

            //岗位关联基本信息
            PortAssociationInfo pai = getPortAssociationInfoDate(page);
            putBeanToPage(page, pai);

            //岗位关联库存记录
            List<PortRelatedInventoryRecords> prirs = getPortRelatedInventoryRecordsDate(page);
            putBeanToPage(page, prirs);

        } else { //不是需要抓取的页面，基本上不可达
            //停止抓取该页面
            page.setSkip(true);
        }

    }

    /**
     * 列表页抽取具体的需要抓取的页面放入抓取队列
     * @param page 抓取到的页面信息
     */
    @SuppressWarnings("unchecked")
    private void putNextPage(Page page) {
        List<String> urlLists = new ArrayList<>();

        Map<?, ?> map = JSON.parseObject(page.getJson().get(), Map.class);

        List<Map<String, Object>> rows = (List<Map<String, Object>>) map.get("rows");
        for (Map<String, Object> mapTemp : rows) {
            //获取具体的融资id
            Integer cargoId = (Integer) mapTemp.get("cargoId");

            urlLists.add(REGULATORY_BUSINESS_URL + cargoId);
            urlLists.add(BUSINESS_DIAGRAM_URL + cargoId);

            //添加岗位关联
            Map<String, String> params = new HashMap<String, String>();
            params.put("hc", (String) mapTemp.get("portInfoHc"));
            params.put("type", URLUtil.urlEncode((String) mapTemp.get("type"), 2));
            params.put("gsdm", (String) mapTemp.get("cbdockcode"));
            params.put("jhh", (String) mapTemp.get("cbjhh"));
            params.put("xh", (String) mapTemp.get("xh"));
            params.put("cargoId", mapTemp.get("cargoId").toString());

            urlLists.add(URLUtil.appendParams(PORT_ASSOCIATION, params));
        }

        //将后续的爬取页面加入
        page.addTargetRequests(urlLists);
    }

    /**
     * 解析融资监管业务图页面解析
     * @param page 抓取到的页面信息
     * @return 
     */
    private List<RegulatoryTimeState> getBusinessDiagramDate(Page page) {
        Html html = page.getHtml();

        String cargoId = URLUtil.urlRequest(page.getUrl().toString()).get("id");

        List<RegulatoryTimeState> list = new ArrayList<RegulatoryTimeState>();

        List<Selectable> selectables = html.xpath("//div[@id='timeLine']/div").nodes();

        //循环整个div块，获取所有的状态信息
        for (int i = 0; i < selectables.size(); i++) {
            Selectable selectable = selectables.get(i);

            List<Selectable> nodes = selectable.xpath("//div[@class='history']/div[@class='history-date']/ul/li")
                    .nodes();

            String stageType = selectable.xpath("//div[@class='tmaxistit']/span/text()").toString();
            //循环获取每个具体的状态信息
            int nodeSize = nodes.size();
            for (int j = 0; j < nodeSize; j++) {
                Selectable node = nodes.get(j);

                RegulatoryTimeState regulatoryTimeState = new RegulatoryTimeState();
                regulatoryTimeState.setCargoId(cargoId);
                regulatoryTimeState.setStageType(stageType);
                String dateString = node.xpath("/li/h3/text()").toString() + "-"
                        + node.xpath("/li/dl/span/text()").toString();
                regulatoryTimeState.setEventDate(DateUtil.parse(dateString, ConstantUtil.DATE_FMT_SECOND));
                regulatoryTimeState.setApplicant(node.xpath("/li/dl/dt[1]/span/text()").toString());
                regulatoryTimeState.setEventState(node.xpath("/li/dl/dt[2]/span/text()").toString());
                regulatoryTimeState.setTimeStateId(cargoId + "_" + (i + 1) + "_" + (nodeSize - j));
                list.add(regulatoryTimeState);
            }

        }

        return list;
    }

    /**
     * 港口关联时间状态图
     * @param page 抓取到的页面信息
     */
    private List<PortAssociationTimeState> getPortAssociationTimeStateDate(Page page) {
        Html html = page.getHtml();
        List<PortAssociationTimeState> patses = new ArrayList<PortAssociationTimeState>();
        String cargoId = URLUtil.urlRequest(page.getUrl().toString()).get("cargoId".toLowerCase());

        List<Selectable> nodes = html.xpath("//div[@class='bg-white']/div[@class='history']/div[1]/ul/li").nodes();
        int nodeSize = nodes.size();
        for (int i = 0; i < nodeSize; i++) {
            Selectable node = nodes.get(i);
            PortAssociationTimeState pats = new PortAssociationTimeState();
            pats.setCargoId(cargoId);
            pats.setTimeStateId(cargoId + "_" + (nodeSize - i));
            pats.setEventState(node.xpath("/li/dl/dt/span/text()").toString());
            pats.setEventDate(
                    DateUtil.parse(node.xpath("/li/h3/text()").toString(), ConstantUtil.DATE_FMT_DEFAULT));

            patses.add(pats);
        }

        return patses;
    }

    /**
     * 港口关联基本信息
     * @param page 抓取到的页面信息
     */
    private PortAssociationInfo getPortAssociationInfoDate(Page page) {
        Html html = page.getHtml();
        PortAssociationInfo pai = new PortAssociationInfo();

        pai.setCargoId(URLUtil.urlRequest(page.getUrl().toString()).get("cargoId".toLowerCase()));
        //船名:奥泰
        pai.setShipName(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[1]/span[2]/text()").toString());
        //英文船名:AUTAI
        pai.setEnglishShipName(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[2]/span[2]/text()").toString());
        //航次:1710
        pai.setVoyageNumber(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[3]/span[2]/text()").toString());
        //代理公司:日照欣荣国际物流有限公司
        pai.setAgencyCompany(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[4]/span[2]/text()").toString());
        //状态:离港
        pai.setState(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[5]/span[2]/text()").toString());
        //位置:西19#
        pai.setLocation(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[6]/span[2]/text()").toString());
        //货名:木薯干
        pai.setGoodsName(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[7]/span[2]/text()").toString());
        //货主:日照昌久国际贸易有限公司
        pai.setOwner(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[8]/span[2]/text()").toString());
        //贸别:外贸
        pai.setTrade(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[9]/span[2]/text()").toString());
        //装卸别:卸
        pai.setLoadUnload(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[10]/span[2]/text()").toString());
        //装卸计划:13582.7
        pai.setLoadPlan(StringUtil.getStringDigitsDouble(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[11]/span[2]/text()").toString()));
        //配载吨:13582.7
        pai.setLoadTon(StringUtil.getStringDigitsDouble(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[12]/span[2]/text()").toString()));
        //交接数:13582.7
        pai.setHandoverNumber(StringUtil.getStringDigitsDouble(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[13]/span[2]/text()").toString()));
        //出发港:归仁
        pai.setDeparturePort(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[14]/span[2]/text()").toString());
        //到达港:日照
        pai.setArrivalPort(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[15]/span[2]/text()").toString());
        //计划靠泊泊位:西19#
        pai.setPlannedBerthingBerth(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[16]/span[2]/text()").toString());
        //计划靠泊说明:左舷靠泊
        pai.setPlanBerthingExplanation(
                html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[17]/span[2]/text()").toString());
        //码头公司:日港裕廊
        pai.setTerminal(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[18]/span[2]/text()").toString());
        //申报人:欣荣
        pai.setDeclarer(html.xpath("//div[@class='bg-white']/div[@class='zl']/ul/li[19]/span[2]/text()").toString());

        return pai;
    }

    /**
     * 港口关联库存记录
     * @param page 抓取到的页面信息
     */
    private List<PortRelatedInventoryRecords> getPortRelatedInventoryRecordsDate(Page page) {
        Html html = page.getHtml();
        List<PortRelatedInventoryRecords> prirs = new ArrayList<PortRelatedInventoryRecords>();
        String cargoId = URLUtil.urlRequest(page.getUrl().toString()).get("cargoId".toLowerCase());

        List<Selectable> nodes = html.xpath("//div[@class='bg-white']/div[@class='row']/div[3]/div/table/tbody/tr")
                .nodes();
        int nodeSize = nodes.size();
        for (int i = 1; i < nodeSize; i++) {
            Selectable node = nodes.get(i);
            PortRelatedInventoryRecords prir = new PortRelatedInventoryRecords();
            prir.setCargoId(cargoId);
            prir.setRecordNumberId(cargoId + "_" + (nodeSize - i - 1));
            //日报日期    
            prir.setDailyDate(DateUtil.parse(node.xpath("/tr/td[1]/text()").toString(), ConstantUtil.DATE_FMT_DAY));
            //垛位  
            prir.setStackPosition(node.xpath("/tr/td[2]/text()").toString());
            //本日入库    
            prir.setTodayStorage(StringUtil.getStringDigitsDouble(node.xpath("/tr/td[3]/text()").toString()));
            //本日出库
            prir.setTodayTreasury(StringUtil.getStringDigitsDouble(node.xpath("/tr/td[4]/text()").toString()));
            //本日库存
            prir.setCurrentStock(StringUtil.getStringDigitsDouble(node.xpath("/tr/td[5]/text()").toString()));

            prirs.add(prir);
        }

        return prirs;
    }

    /**
     * 融资监管业务单页面解析
     * @param page 抓取到的页面信息
     */
    private MyBatisPojo getRegulatoryBusinessUrlDate(Page page) {
        Html html = page.getHtml();

        RegulatoryBusiness regulatoryBusiness = new RegulatoryBusiness();

        regulatoryBusiness.setCargoId(URLUtil.urlRequest(page.getUrl().toString()).get("cargoId".toLowerCase()));
        //======================================================>
        //客户名称
        regulatoryBusiness.setCustomerName(html.xpath("//label[@id='khmc']/text()").toString());

        //编号
        regulatoryBusiness.setNumberId(html.xpath("//label[@id='cargoAction_cargoBase_cargoNo']/text()").toString());
        //日期
        regulatoryBusiness.setCreateCustomerDate(
                DateUtil.parse(html.xpath("//label[@id='sqdrq']/text()").toString(), ConstantUtil.DATE_FMT_DAY));
        //======================================================>

        //======================================================>
        //融资方式
        regulatoryBusiness.setFinancingMode(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[1]/text()")
                        .toString());
        //港  口
        regulatoryBusiness.setHarbour(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[1]/table/tbody/tr[2]/td[1]/text()")
                        .toString());
        //码头公司
        regulatoryBusiness.setWharfCompany(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[1]/table/tbody/tr[3]/td[1]/text()")
                        .toString());
        //货  类
        regulatoryBusiness.setCargo(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[1]/table/tbody/tr[4]/td[1]/text()")
                        .toString());
        //货  名
        regulatoryBusiness.setArticleName(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[1]/table/tbody/tr[5]/td[1]/text()")
                        .toString());
        //======================================================>

        //======================================================>
        //银行
        regulatoryBusiness.setBank(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[2]/table/tbody/tr[1]/td[1]/text()")
                        .toString());
        //监管公司
        regulatoryBusiness.setRegulatoryCompany(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[2]/table/tbody/tr[2]/td[1]/text()")
                        .toString());
        //担保公司
        regulatoryBusiness.setGuaranteeCorporation(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[2]/table/tbody/tr[3]/td[1]/text()")
                        .toString());
        //船  代
        regulatoryBusiness.setShippingAgency(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[2]/table/tbody/tr[4]/td[1]/text()")
                        .toString());
        ;
        //货  代
        regulatoryBusiness.setFreightForwarding(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[2]/table/tbody/tr[5]/td[1]/text()")
                        .toString());
        //======================================================>

        //======================================================>
        //货物数量
        regulatoryBusiness.setGoodsQuantity(StringUtil.getStringDigitsDouble(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[3]/table/tbody/tr[1]/td[1]/text()")
                        .toString()));

        //融资金额
        regulatoryBusiness.setFinancingAmount(StringUtil.getStringDigitsDouble(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[3]/table/tbody/tr[2]/td[1]/text()")
                        .toString()));

        //融资周期
        regulatoryBusiness.setFinancingCycle(StringUtil.getStringDigitsInteger(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[1]/td[3]/table/tbody/tr[3]/td[1]/text()")
                        .toString()));
        //======================================================>

        //======================================================>
        //船名
        regulatoryBusiness
                .setShipName(html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[2]/td[2]/text()").toString());
        //收到提单日
        regulatoryBusiness.setLadingReceiptDate(
                DateUtil.parse(html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[3]/td[2]/text()").toString(),
                        ConstantUtil.DATE_FMT_DAY));
        //产地
        regulatoryBusiness
                .setPlaceOfOrigin(html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[4]/td[2]/text()").toString());
        //型号
        regulatoryBusiness
                .setModel(html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[5]/td[2]/text()").toString());
        //规格
        regulatoryBusiness.setSpecifications(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[6]/td[2]/text()").toString());
        //电子仓单编号
        regulatoryBusiness.setElectronicReceiptId(
                html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[7]/td[2]/text()").toString());
        //======================================================>

        //======================================================>
        //委托人
        regulatoryBusiness.setElectronicClient(
                html.xpath("//label[@id='cargoAction_cargoBase_portInfoTzhdWtr']/text()").toString());
        //垛位
        regulatoryBusiness
                .setStackPosition(html.xpath("//label[@id='cargoAction_cargoBase_portInfoDw']/text()").toString());
        //舱单数
        regulatoryBusiness.setCabinNumber(StringUtil.getStringDigitsDouble(
                html.xpath("//label[@id='cargoAction_cargoBase_portInfoTzhdCdshl']/text()").toString()));
        //到港时间
        regulatoryBusiness.setPortTime(DateUtil.parse(
                html.xpath("//label[@id='cargoAction_cargoBase_portInfoTzhdDgshjStr']/text()").toString(),
                ConstantUtil.DATE_FMT_SECOND));
        //======================================================>

        return regulatoryBusiness;
    }

    /**
     * 赎货申请
     */
    private List<RedemptionApplication> getRedemptionApplicationFormDate(Page page) {
        Html html = page.getHtml();

        String cargoId = URLUtil.urlRequest(page.getUrl().toString()).get("cargoId".toLowerCase());
        String numberId = html.xpath("//label[@id='cargoAction_cargoBase_cargoNo']/text()").toString();

        List<RedemptionApplication> redemptionApplications = new ArrayList<RedemptionApplication>();

        List<Selectable> nodes = html.xpath("//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr")
                .nodes();
        for (int i = 1; i < nodes.size(); i++) {
            RedemptionApplication redemptionApplication = new RedemptionApplication();
            redemptionApplication.setCargoId(cargoId);
            redemptionApplication.setNumberId(numberId + "-" + String.format("%02d", i));

            //申请账号
            redemptionApplication.setApplicationAccount(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[1]/text()")
                    .toString());
            //赎货数量
            redemptionApplication.setRedemptionQuantity(StringUtil.getStringDigitsDouble(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[2]/text()")
                    .toString()));
            //已还贷金额
            redemptionApplication.setRepaidMoney(StringUtil.getStringDigitsDouble(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[3]/text()")
                    .toString()));
            //最终提报时间
            redemptionApplication.setFinalPresentationTime(DateUtil.parse(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[4]/text()")
                    .toString(), ConstantUtil.DATE_FMT_DEFAULT));
            //审核通过时间
            redemptionApplication.setAuditPassTime(DateUtil.parse(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[5]/text()")
                    .toString(), ConstantUtil.DATE_FMT_DEFAULT));
            //当前状态
            redemptionApplication.setCurrentState(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[6]/text()")
                    .toString());
            //审核人
            redemptionApplication.setAuditor(html.xpath(
                    "//form[@id='cargoAction']/table[2]/tbody/tr[14]/td/table/tbody/tr[" + (i + 1) + "]/td[7]/text()")
                    .toString());

            redemptionApplications.add(redemptionApplication);

        }

        return redemptionApplications;
    }

}