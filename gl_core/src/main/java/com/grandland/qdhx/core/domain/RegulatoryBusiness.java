package com.grandland.qdhx.core.domain;

import java.util.Date;

import com.grandland.qdhx.core.annotation.TableId;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 融资监管业务单
 * @author loverqi
 * @date 2018年1月31日
 */
@TableId(idName = "cargoId")
public class RegulatoryBusiness extends MyBatisPojo {
    private static final long serialVersionUID = -986587626788883232L;

    /** 主键id*/
    private Integer id;

    /** 融资单实际id*/
    private String cargoId;

    /** 客户名称*/
    private String customerName;

    /** 编号*/
    private String numberId;

    /** 日期*/
    private Date createCustomerDate;

    /** 融资方式*/
    private String financingMode;

    /** 港口*/
    private String harbour;

    /** 码头公司*/
    private String wharfCompany;

    /** 货类*/
    private String cargo;

    /** 货名*/
    private String articleName;

    /** 银行*/
    private String bank;

    /** 监管公司*/
    private String regulatoryCompany;

    /** 担保公司*/
    private String guaranteeCorporation;

    /** 船代*/
    private String shippingAgency;

    /** 货代*/
    private String freightForwarding;

    /** 货物数量*/
    private Double goodsQuantity;

    /** 融资金额*/
    private Double financingAmount;

    /** 融资周期*/
    private Integer financingCycle;

    /** 船名*/
    private String shipName;

    /** 收到提单日*/
    private Date ladingReceiptDate;

    /** 产地*/
    private String placeOfOrigin;

    /** 型号*/
    private String model;

    /** 规格*/
    private String specifications;

    /** 电子仓单编号*/
    private String electronicReceiptId;

    /** 委托人*/
    private String electronicClient;

    /** 垛位*/
    private String stackPosition;

    /** 舱单数*/
    private Double cabinNumber;

    /** 到港时间*/
    private Date portTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId == null ? null : cargoId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId == null ? null : numberId.trim();
    }

    public Date getCreateCustomerDate() {
        return createCustomerDate;
    }

    public void setCreateCustomerDate(Date createCustomerDate) {
        this.createCustomerDate = createCustomerDate;
    }

    public String getFinancingMode() {
        return financingMode;
    }

    public void setFinancingMode(String financingMode) {
        this.financingMode = financingMode == null ? null : financingMode.trim();
    }

    public String getHarbour() {
        return harbour;
    }

    public void setHarbour(String harbour) {
        this.harbour = harbour == null ? null : harbour.trim();
    }

    public String getWharfCompany() {
        return wharfCompany;
    }

    public void setWharfCompany(String wharfCompany) {
        this.wharfCompany = wharfCompany == null ? null : wharfCompany.trim();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo == null ? null : cargo.trim();
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName == null ? null : articleName.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getRegulatoryCompany() {
        return regulatoryCompany;
    }

    public void setRegulatoryCompany(String regulatoryCompany) {
        this.regulatoryCompany = regulatoryCompany == null ? null : regulatoryCompany.trim();
    }

    public String getGuaranteeCorporation() {
        return guaranteeCorporation;
    }

    public void setGuaranteeCorporation(String guaranteeCorporation) {
        this.guaranteeCorporation = guaranteeCorporation == null ? null : guaranteeCorporation.trim();
    }

    public String getShippingAgency() {
        return shippingAgency;
    }

    public void setShippingAgency(String shippingAgency) {
        this.shippingAgency = shippingAgency == null ? null : shippingAgency.trim();
    }

    public String getFreightForwarding() {
        return freightForwarding;
    }

    public void setFreightForwarding(String freightForwarding) {
        this.freightForwarding = freightForwarding == null ? null : freightForwarding.trim();
    }

    public Double getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Double goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public Double getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(Double financingAmount) {
        this.financingAmount = financingAmount;
    }

    public Integer getFinancingCycle() {
        return financingCycle;
    }

    public void setFinancingCycle(Integer financingCycle) {
        this.financingCycle = financingCycle;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName == null ? null : shipName.trim();
    }

    public Date getLadingReceiptDate() {
        return ladingReceiptDate;
    }

    public void setLadingReceiptDate(Date ladingReceiptDate) {
        this.ladingReceiptDate = ladingReceiptDate;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin == null ? null : placeOfOrigin.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getElectronicReceiptId() {
        return electronicReceiptId;
    }

    public void setElectronicReceiptId(String electronicReceiptId) {
        this.electronicReceiptId = electronicReceiptId == null ? null : electronicReceiptId.trim();
    }

    public String getElectronicClient() {
        return electronicClient;
    }

    public void setElectronicClient(String electronicClient) {
        this.electronicClient = electronicClient == null ? null : electronicClient.trim();
    }

    public String getStackPosition() {
        return stackPosition;
    }

    public void setStackPosition(String stackPosition) {
        this.stackPosition = stackPosition == null ? null : stackPosition.trim();
    }

    public Double getCabinNumber() {
        return cabinNumber;
    }

    public void setCabinNumber(Double cabinNumber) {
        this.cabinNumber = cabinNumber;
    }

    public Date getPortTime() {
        return portTime;
    }

    public void setPortTime(Date portTime) {
        this.portTime = portTime;
    }
}