package com.grandland.qdhx.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单表的实体类
 * @author loverqi
 * @date 2018年1月5日
 */
@ApiModel(value = "权限对象类")
public class Permissions extends GlSysFunc {

    private static final long serialVersionUID = -6286782749391133561L;

    /** 是否得到授权*/
    @ApiModelProperty(value = "是否得到授权")
    private boolean isSelected;

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    @JsonIgnore
    public String getPath() {
        return super.getPath();
    }

    public String getName() {
        return super.getText();
    }

    @Override
    @JsonIgnore
    public String getIcon() {
        return super.getIcon();
    }

}