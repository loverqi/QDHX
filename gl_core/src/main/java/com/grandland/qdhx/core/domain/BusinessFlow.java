package com.grandland.qdhx.core.domain;

import java.util.Date;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 
 */
public class BusinessFlow extends MyBatisPojo{
    /**
     * 业务流程主键
     */
    private Integer id;

    /**
     * 业务数据id
     */
    private Integer bumorId;

    /**
     * 业务操作状态
     */
    private Integer operateState;

    /**
     * 业务操作理由
     */
    private String reason;

    /**
     * 是否被删除
     */
    private Integer isDeleted;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

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

    public Integer getBumorId() {
        return bumorId;
    }

    public void setBumorId(Integer bumorId) {
        this.bumorId = bumorId;
    }

    public Integer getOperateState() {
        return operateState;
    }

    public void setOperateState(Integer operateState) {
        this.operateState = operateState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}