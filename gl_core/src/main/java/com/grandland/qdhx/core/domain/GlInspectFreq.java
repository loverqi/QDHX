package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 巡核库巡查频率
 */
public class GlInspectFreq extends MyBatisPojo {

    /** */
    private static final long serialVersionUID = 3194847010502978350L;

    /** 自增主键 */
    private Integer id;

    /** 单位类型 */
    private String deptType;

    /** 监管级别 */
    private Integer level;

    /** 巡核时间数值 */
    private Integer num;

    /** 时间单位 */
    private String unit;

    /** 是否已删除 */
    private boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GlInspectFreq freq = (GlInspectFreq) o;

        return id != null ? id.equals(freq.id) : freq.id == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GlInspectFreq{" +
                "id=" + id +
                ", deptType='" + deptType + '\'' +
                ", level=" + level +
                ", num=" + num +
                ", unit='" + unit + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
