package com.grandland.qdhx.core.domain;

import java.util.List;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;
import com.grandland.qdhx.treedata.INode;

/**
 * 部门岗位人员层级菜单
 * @author loverqi
 * @date 2018年2月8日
 */
public class Level extends MyBatisPojo implements INode<Level> {

    private static final long serialVersionUID = -8512103106748084546L;

    private String id;
    private String pid;
    private String name;
    private List<Level> children;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Level> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<Level> children) {
        this.children = children;
    }

}
