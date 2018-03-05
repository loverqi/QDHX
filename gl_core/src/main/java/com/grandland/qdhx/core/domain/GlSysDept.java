package com.grandland.qdhx.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 部门对象
 * @author loverqi
 * @date 2018年1月15日
 */
@ApiModel(value = "部门对象类")
public class GlSysDept extends MyBatisPojo {

    private static final long serialVersionUID = -2589857437721179798L;

    /** 部门编号*/
    @ApiModelProperty(value = "部门编号")
    private Integer id;

    /** 部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /** 是否启用或是否显示*/
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    @JsonIgnore
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}