package com.grandland.qdhx.core.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grandland.qdhx.core.annotation.FieldIgnore;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;
import com.grandland.qdhx.treedata.INode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单表的实体类
 * @author loverqi
 * @date 2018年1月5日
 */
@ApiModel(value = "菜单对象类")
public class GlSysFunc extends MyBatisPojo implements INode<GlSysFunc> {

    private static final long serialVersionUID = 587603712233362065L;

    /** 菜单编号*/
    @ApiModelProperty(value = "菜单编号")
    private String id;

    /** 路径*/
    @ApiModelProperty(value = "路径")
    private String path;

    /** 父目录*/
    @ApiModelProperty(value = "父目录")
    private String parent;

    /** 显示内容*/
    @ApiModelProperty(value = "显示内容")
    private String text;

    /** 图标*/
    @ApiModelProperty(value = "图标")
    private String icon;

    /** 子层目录*/
    @FieldIgnore
    @ApiModelProperty(value = "子层目录")
    private List<GlSysFunc> children;

    /** 是否启用*/
    private Boolean enable;

    /** 权限英文名*/
    private String funcName;

    /** 是否是末级菜单*/
    private Boolean isLeaf;

    /** 是否启用权限管理*/
    private Boolean isJuris;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public List<GlSysFunc> getChildren() {
        return children;
    }

    public void setChildren(List<GlSysFunc> children) {
        this.children = children;
    }

    @JsonIgnore
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @JsonIgnore
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @JsonIgnore
    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public Boolean getIsJuris() {
        return isJuris;
    }

    public void setIsJuris(Boolean isJuris) {
        this.isJuris = isJuris;
    }

    @Override
    public String getPid() {
        return parent;
    }

    @Override
    public void setPid(String pid) {
        this.parent = pid;
    }
}