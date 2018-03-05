package com.grandland.qdhx.core.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * @author 
 */
public class BusinessFall extends MyBatisPojo {
    /**
     * 跌价补偿记录id
     */
    private Integer id;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 中华商务网价格id
     */
    private Integer priceId;

    /**
     * 是否跌价
     */
    private Integer isDrop;

    /**
     * 跌价金额
     */
    private BigDecimal dropValue;

    /**
     * 现在价格
     */
    private BigDecimal price;

    /**
     * 现有价值
     */
    private BigDecimal value;

    /**
     * 跌去的价值(percent)
     */
    private String percent;

    /**
     * 是否删除
     */
    private Integer isDeleted;

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

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public Integer getIsDrop() {
        return isDrop;
    }

    public void setIsDrop(Integer isDrop) {
        this.isDrop = isDrop;
    }

    public BigDecimal getDropValue() {
        return dropValue;
    }

    public void setDropValue(BigDecimal dropValue) {
        this.dropValue = dropValue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
        BusinessFall other = (BusinessFall) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getPriceId() == null ? other.getPriceId() == null : this.getPriceId().equals(other.getPriceId()))
            && (this.getIsDrop() == null ? other.getIsDrop() == null : this.getIsDrop().equals(other.getIsDrop()))
            && (this.getDropValue() == null ? other.getDropValue() == null : this.getDropValue().equals(other.getDropValue()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getPriceId() == null) ? 0 : getPriceId().hashCode());
        result = prime * result + ((getIsDrop() == null) ? 0 : getIsDrop().hashCode());
        result = prime * result + ((getDropValue() == null) ? 0 : getDropValue().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
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
        sb.append(", priceId=").append(priceId);
        sb.append(", isDrop=").append(isDrop);
        sb.append(", dropValue=").append(dropValue);
        sb.append(", price=").append(price);
        sb.append(", value=").append(value);
        sb.append(", percent=").append(percent);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}