package com.grandland.qdhx.core.domain;

import java.util.Date;

import com.grandland.qdhx.core.annotation.TableId;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 赎货申请单
 * @author loverqi
 * @date 2018年2月2日
 */
@TableId(idName = "numberId")
public class RedemptionApplication extends MyBatisPojo {

    private static final long serialVersionUID = -3990332699739403187L;

    /** 主键id*/
    private Integer id;

    /** 融资单实际id*/
    private String cargoId;

    /** 赎货编号*/
    private String numberId;

    /** 申请账号*/
    private String applicationAccount;

    /** 赎货数量*/
    private Double redemptionQuantity;

    /** 已还贷金额*/
    private Double repaidMoney;

    /** 最终提报时间*/
    private Date finalPresentationTime;

    /** 审核通过时间*/
    private Date auditPassTime;

    /** 当前状态*/
    private String currentState;

    /** 审核人*/
    private String auditor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getApplicationAccount() {
        return applicationAccount;
    }

    public void setApplicationAccount(String applicationAccount) {
        this.applicationAccount = applicationAccount == null ? null : applicationAccount.trim();
    }

    public Double getRedemptionQuantity() {
        return redemptionQuantity;
    }

    public void setRedemptionQuantity(Double redemptionQuantity) {
        this.redemptionQuantity = redemptionQuantity;
    }

    public Double getRepaidMoney() {
        return repaidMoney;
    }

    public void setRepaidMoney(Double repaidMoney) {
        this.repaidMoney = repaidMoney;
    }

    public Date getFinalPresentationTime() {
        return finalPresentationTime;
    }

    public void setFinalPresentationTime(Date finalPresentationTime) {
        this.finalPresentationTime = finalPresentationTime;
    }

    public Date getAuditPassTime() {
        return auditPassTime;
    }

    public void setAuditPassTime(Date auditPassTime) {
        this.auditPassTime = auditPassTime;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState == null ? null : currentState.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

}