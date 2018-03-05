package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * @liuzhaomeng
 */
public class BusinessClassify extends MyBatisPojo{
    /**
     * 节点id
     */
    private String id;

    /**
     * 父节点id
     */
    private String pid;

    /**
     * 节点名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}