package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class BudeadLine extends MyBatisPojo {
    private Integer id;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 日照港id
     */
    private String rizhaoId;

    /**
     * 是否赎货 0未赎货 1已赎货
     */
    private Integer isShuhuo;

    /**
     * 到期时间
     */
    private Date deadLine;

    /**
     * 已赎货金额
     */
    private BigDecimal shuhuoValue;

    /**
     * 已赎货数量
     */
    private BigDecimal shuhuoCount;

    /**
     * 未赎货金额
     */
    private BigDecimal notRedeemValue;

    /**
     * 未赎货数量
     */
    private BigDecimal notRedeemCount;

    /**
     * 已赎货次数
     */
    private Integer shuhuoTimes;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getRizhaoId() {
        return rizhaoId;
    }

    public void setRizhaoId(String rizhaoId) {
        this.rizhaoId = rizhaoId;
    }

    public Integer getIsShuhuo() {
        return isShuhuo;
    }

    public void setIsShuhuo(Integer isShuhuo) {
        this.isShuhuo = isShuhuo;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public BigDecimal getShuhuoValue() {
        return shuhuoValue;
    }

    public void setShuhuoValue(BigDecimal shuhuoValue) {
        this.shuhuoValue = shuhuoValue;
    }

    public BigDecimal getShuhuoCount() {
        return shuhuoCount;
    }

    public void setShuhuoCount(BigDecimal shuhuoCount) {
        this.shuhuoCount = shuhuoCount;
    }

    public BigDecimal getNotRedeemValue() {
        return notRedeemValue;
    }

    public void setNotRedeemValue(BigDecimal notRedeemValue) {
        this.notRedeemValue = notRedeemValue;
    }

    public BigDecimal getNotRedeemCount() {
        return notRedeemCount;
    }

    public void setNotRedeemCount(BigDecimal notRedeemCount) {
        this.notRedeemCount = notRedeemCount;
    }

    public Integer getShuhuoTimes() {
        return shuhuoTimes;
    }

    public void setShuhuoTimes(Integer shuhuoTimes) {
        this.shuhuoTimes = shuhuoTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BudeadLine other = (BudeadLine) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getRizhaoId() == null ? other.getRizhaoId() == null : this.getRizhaoId().equals(other.getRizhaoId()))
            && (this.getIsShuhuo() == null ? other.getIsShuhuo() == null : this.getIsShuhuo().equals(other.getIsShuhuo()))
            && (this.getDeadLine() == null ? other.getDeadLine() == null : this.getDeadLine().equals(other.getDeadLine()))
            && (this.getShuhuoValue() == null ? other.getShuhuoValue() == null : this.getShuhuoValue().equals(other.getShuhuoValue()))
            && (this.getShuhuoCount() == null ? other.getShuhuoCount() == null : this.getShuhuoCount().equals(other.getShuhuoCount()))
            && (this.getShuhuoTimes() == null ? other.getShuhuoTimes() == null : this.getShuhuoTimes().equals(other.getShuhuoTimes()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getRizhaoId() == null) ? 0 : getRizhaoId().hashCode());
        result = prime * result + ((getIsShuhuo() == null) ? 0 : getIsShuhuo().hashCode());
        result = prime * result + ((getDeadLine() == null) ? 0 : getDeadLine().hashCode());
        result = prime * result + ((getShuhuoValue() == null) ? 0 : getShuhuoValue().hashCode());
        result = prime * result + ((getShuhuoCount() == null) ? 0 : getShuhuoCount().hashCode());
        result = prime * result + ((getShuhuoTimes() == null) ? 0 : getShuhuoTimes().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessId=").append(businessId);
        sb.append(", rizhaoId=").append(rizhaoId);
        sb.append(", isShuhuo=").append(isShuhuo);
        sb.append(", deadLine=").append(deadLine);
        sb.append(", shuhuoValue=").append(shuhuoValue);
        sb.append(", shuhuoCount=").append(shuhuoCount);
        sb.append(", notRedeemValue=").append(notRedeemValue);
        sb.append(", notRedeemCount=").append(notRedeemCount);
        sb.append(", shuhuoTimes=").append(shuhuoTimes);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}