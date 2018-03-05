package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.annotation.TableId;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 港口关联基本信息
 * @author loverqi
 * @date 2018年2月12日
 */
@TableId(idName = "cargoId")
public class PortAssociationInfo extends MyBatisPojo {
    private static final long serialVersionUID = -1374504060102615258L;

    /** 主键id*/
    private Integer id;

    /** 融资单实际id*/
    private String cargoId;

    /** 船名*/
    private String shipName;

    /** 英文船名*/
    private String englishShipName;

    /** 航次*/
    private String voyageNumber;

    /** 代理公司*/
    private String agencyCompany;

    /** 状态*/
    private String state;

    /** 位置*/
    private String location;

    /** 货名*/
    private String goodsName;

    /** 货主*/
    private String owner;

    /** 贸别*/
    private String trade;

    /** 装卸别*/
    private String loadUnload;

    /** 装卸计划*/
    private Double loadPlan;

    /** 配载吨*/
    private Double loadTon;

    /** 交接数*/
    private Double handoverNumber;

    /** 出发港*/
    private String departurePort;

    /** 到达港*/
    private String arrivalPort;

    /** 计划靠泊泊位*/
    private String plannedBerthingBerth;

    /** 计划靠泊说明*/
    private String planBerthingExplanation;

    /** 码头公司*/
    private String terminal;

    /** 申报人*/
    private String declarer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String string) {
        this.cargoId = string;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName == null ? null : shipName.trim();
    }

    public String getEnglishShipName() {
        return englishShipName;
    }

    public void setEnglishShipName(String englishShipName) {
        this.englishShipName = englishShipName == null ? null : englishShipName.trim();
    }

    public String getAgencyCompany() {
        return agencyCompany;
    }

    public void setAgencyCompany(String agencyCompany) {
        this.agencyCompany = agencyCompany == null ? null : agencyCompany.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade == null ? null : trade.trim();
    }

    public String getLoadUnload() {
        return loadUnload;
    }

    public void setLoadUnload(String loadUnload) {
        this.loadUnload = loadUnload == null ? null : loadUnload.trim();
    }

    public Double getLoadPlan() {
        return loadPlan;
    }

    public void setLoadPlan(Double loadPlan) {
        this.loadPlan = loadPlan;
    }

    public Double getLoadTon() {
        return loadTon;
    }

    public void setLoadTon(Double loadTon) {
        this.loadTon = loadTon;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public Double getHandoverNumber() {
        return handoverNumber;
    }

    public void setHandoverNumber(Double handoverNumber) {
        this.handoverNumber = handoverNumber;
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(String departurePort) {
        this.departurePort = departurePort == null ? null : departurePort.trim();
    }

    public String getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(String arrivalPort) {
        this.arrivalPort = arrivalPort == null ? null : arrivalPort.trim();
    }

    public String getPlannedBerthingBerth() {
        return plannedBerthingBerth;
    }

    public void setPlannedBerthingBerth(String plannedBerthingBerth) {
        this.plannedBerthingBerth = plannedBerthingBerth == null ? null : plannedBerthingBerth.trim();
    }

    public String getPlanBerthingExplanation() {
        return planBerthingExplanation;
    }

    public void setPlanBerthingExplanation(String planBerthingExplanation) {
        this.planBerthingExplanation = planBerthingExplanation == null ? null : planBerthingExplanation.trim();
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getDeclarer() {
        return declarer;
    }

    public void setDeclarer(String declarer) {
        this.declarer = declarer == null ? null : declarer.trim();
    }
}