package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限对象
 * @author loverqi
 * @date 2018年1月15日
 */
@ApiModel(value = "权限对象")
public class GlSysPriv extends MyBatisPojo {

    private static final long serialVersionUID = -5060313293234646247L;

    /** 主键id*/
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /** 岗位编号*/
    @ApiModelProperty(value = "岗位编号")
    private Integer postId;

    /** 菜单编号*/
    @ApiModelProperty(value = "菜单编号")
    private Integer funcId;

    /** 是否启用*/
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((funcId == null) ? 0 : funcId.hashCode());
        result = prime * result + ((postId == null) ? 0 : postId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GlSysPriv other = (GlSysPriv) obj;
        if (funcId == null) {
            if (other.funcId != null)
                return false;
        } else if (!funcId.equals(other.funcId))
            return false;
        if (postId == null) {
            if (other.postId != null)
                return false;
        } else if (!postId.equals(other.postId))
            return false;
        return true;
    }

}