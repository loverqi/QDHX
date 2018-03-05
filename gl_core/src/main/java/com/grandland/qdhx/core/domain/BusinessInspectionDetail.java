package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import java.util.Date;

/**
 * 巡核库详情实体类
 */
public class BusinessInspectionDetail extends MyBatisPojo {
    private static final long serialVersionUID = 1L;

    /**
     * 详情id
     */
    private Integer id;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 吸存号
     */
    private Integer userId;

    /**
     * 是否已巡查
     */
    private boolean isChecked;

    /**
     * 巡查时间
     */
    private Date checkTime;

    /**
     * 实际巡查时间
     */
    private Date realCheckTime;

    /**
     * 巡核库实地图片
     */
    private String imgs;

    /**
     * 公司名称
     */
    private String corporate;

    /**
     * 存放地址
     */
    private String address;

    /**
     * 定位
     */
    private String location;

    /**
     * 定位是否准确
     */
    private boolean isAccurate;

    /**
     * 其它信息
     */
    private String extra;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getRealCheckTime() {
        return realCheckTime;
    }

    public void setRealCheckTime(Date realCheckTime) {
        this.realCheckTime = realCheckTime;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAccurate() {
        return isAccurate;
    }

    public void setAccurate(boolean accurate) {
        isAccurate = accurate;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessId=").append(businessId);
        sb.append(", userId=").append(userId);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", img=").append(imgs);
        sb.append(", corporate=").append(corporate);
        sb.append(", address=").append(address);
        sb.append(", location=").append(location);
        sb.append(", isAccurate=").append(isAccurate);
        sb.append(", extra=").append(extra);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}