package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import java.util.Date;

/**
 * 监管机构表
 */
public class GlSuperUnit extends MyBatisPojo {

	private static final long serialVersionUID = -4752261554905057826L;

	/** 自增主键 */
    private Integer id;

    /** 监管机构代码 */
    private String super_code;

    /** 监管机构名称 */
    private String super_unit;

    /** 监管级别 */
    private Integer level;

    /** 是否已删除 */
    private boolean isDeleted;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuper_code() {
        return super_code;
    }

    public void setSuper_code(String super_code) {
        this.super_code = super_code;
    }

    public String getSuper_unit() {
        return super_unit;
    }

    public void setSuper_unit(String super_unit) {
        this.super_unit = super_unit;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    @Override
    public String toString() {
        return "GlSuperUnit{" +
                "id=" + id +
                ", super_code='" + super_code + '\'' +
                ", super_unit='" + super_unit + '\'' +
                ", level=" + level +
                ", isDeleted=" + isDeleted +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
