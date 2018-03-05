package com.grandland.qdhx.core.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class GlPriceInfo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期-天
     */
    private String day;

    /**
     * 地区
     */
    private String area;

    /**
     * 省份
     */
    private String province;

    /**
     * 市场
     */
    private String market;

    /**
     * 产品名称
     */
    private String product;

    /**
     * 材质
     */
    private String material;

    /**
     * 规格
     */
    private String specification;

    /**
     * 价格
     */
    private String price;

    /**
     * 价格涨跌
     */
    private String priceChange;

    /**
     * 价格涨跌值
     */
    private String priceChangeValue;

    /**
     * 价格涨跌率
     */
    private String priceChangeReate;

    /**
     * 计量单位
     */
    private String measureUnit;

    /**
     * 价格单位
     */
    private String priceUnit;

    /**
     * 生产厂家
     */
    private String vendor;

    /**
     * 价格类型
     */
    private String priceType;

    /**
     * 备注
     */
    private String bz;

    /**
     * 是否含税
     */
    private Boolean isIncludeTax;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangeValue() {
        return priceChangeValue;
    }

    public void setPriceChangeValue(String priceChangeValue) {
        this.priceChangeValue = priceChangeValue;
    }

    public String getPriceChangeReate() {
        return priceChangeReate;
    }

    public void setPriceChangeReate(String priceChangeReate) {
        this.priceChangeReate = priceChangeReate;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Boolean getIsIncludeTax() {
        return isIncludeTax;
    }

    public void setIsIncludeTax(Boolean isIncludeTax) {
        this.isIncludeTax = isIncludeTax;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        GlPriceInfo other = (GlPriceInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getMarket() == null ? other.getMarket() == null : this.getMarket().equals(other.getMarket()))
            && (this.getProduct() == null ? other.getProduct() == null : this.getProduct().equals(other.getProduct()))
            && (this.getMaterial() == null ? other.getMaterial() == null : this.getMaterial().equals(other.getMaterial()))
            && (this.getSpecification() == null ? other.getSpecification() == null : this.getSpecification().equals(other.getSpecification()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getPriceChange() == null ? other.getPriceChange() == null : this.getPriceChange().equals(other.getPriceChange()))
            && (this.getPriceChangeValue() == null ? other.getPriceChangeValue() == null : this.getPriceChangeValue().equals(other.getPriceChangeValue()))
            && (this.getPriceChangeReate() == null ? other.getPriceChangeReate() == null : this.getPriceChangeReate().equals(other.getPriceChangeReate()))
            && (this.getMeasureUnit() == null ? other.getMeasureUnit() == null : this.getMeasureUnit().equals(other.getMeasureUnit()))
            && (this.getPriceUnit() == null ? other.getPriceUnit() == null : this.getPriceUnit().equals(other.getPriceUnit()))
            && (this.getVendor() == null ? other.getVendor() == null : this.getVendor().equals(other.getVendor()))
            && (this.getPriceType() == null ? other.getPriceType() == null : this.getPriceType().equals(other.getPriceType()))
            && (this.getBz() == null ? other.getBz() == null : this.getBz().equals(other.getBz()))
            && (this.getIsIncludeTax() == null ? other.getIsIncludeTax() == null : this.getIsIncludeTax().equals(other.getIsIncludeTax()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getMarket() == null) ? 0 : getMarket().hashCode());
        result = prime * result + ((getProduct() == null) ? 0 : getProduct().hashCode());
        result = prime * result + ((getMaterial() == null) ? 0 : getMaterial().hashCode());
        result = prime * result + ((getSpecification() == null) ? 0 : getSpecification().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getPriceChange() == null) ? 0 : getPriceChange().hashCode());
        result = prime * result + ((getPriceChangeValue() == null) ? 0 : getPriceChangeValue().hashCode());
        result = prime * result + ((getPriceChangeReate() == null) ? 0 : getPriceChangeReate().hashCode());
        result = prime * result + ((getMeasureUnit() == null) ? 0 : getMeasureUnit().hashCode());
        result = prime * result + ((getPriceUnit() == null) ? 0 : getPriceUnit().hashCode());
        result = prime * result + ((getVendor() == null) ? 0 : getVendor().hashCode());
        result = prime * result + ((getPriceType() == null) ? 0 : getPriceType().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        result = prime * result + ((getIsIncludeTax() == null) ? 0 : getIsIncludeTax().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", day=").append(day);
        sb.append(", area=").append(area);
        sb.append(", province=").append(province);
        sb.append(", market=").append(market);
        sb.append(", product=").append(product);
        sb.append(", material=").append(material);
        sb.append(", specification=").append(specification);
        sb.append(", price=").append(price);
        sb.append(", priceChange=").append(priceChange);
        sb.append(", priceChangeValue=").append(priceChangeValue);
        sb.append(", priceChangeReate=").append(priceChangeReate);
        sb.append(", measureUnit=").append(measureUnit);
        sb.append(", priceUnit=").append(priceUnit);
        sb.append(", vendor=").append(vendor);
        sb.append(", priceType=").append(priceType);
        sb.append(", bz=").append(bz);
        sb.append(", isIncludeTax=").append(isIncludeTax);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}