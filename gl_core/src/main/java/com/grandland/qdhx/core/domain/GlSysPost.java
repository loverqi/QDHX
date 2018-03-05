package com.grandland.qdhx.core.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.grandland.qdhx.core.annotation.FieldIgnore;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 岗位对象
 * @author loverqi
 * @date 2018年1月15日
 */
@ApiModel(value = "岗位对象")
public class GlSysPost extends MyBatisPojo {

    private static final long serialVersionUID = -609562367481495591L;

    /** 主键id*/
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /** 岗位名称*/
    @ApiModelProperty(value = "岗位名称")
    private String postName;

    /** 部门编号*/
    @ApiModelProperty(value = "部门编号")
    private Integer deptId;

    /** 部门编号*/
    @FieldIgnore
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /** 部门的权限列表*/
    @FieldIgnore
    @ApiModelProperty(value = "权限列表")
    @JsonProperty(access = Access.READ_ONLY)
    private List<String> privs;

    @FieldIgnore
    @ApiModelProperty(value = "权限id列表")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Integer> privIds;

    /** 是否可用或是否显示*/
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @JsonIgnore
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<String> getPrivs() {
        return privs;
    }

    public void setPrivs(List<String> privs) {
        this.privs = privs;
    }

    public List<Integer> getPrivIds() {
        return privIds;
    }

    public void setPrivIds(List<Integer> privIds) {
        this.privIds = privIds;
    }

}