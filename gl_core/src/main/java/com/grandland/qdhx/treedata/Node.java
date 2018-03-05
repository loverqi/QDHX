package com.grandland.qdhx.treedata;

import java.util.List;

public class Node implements INode<Node> {
    private String id;
    private String pid;
    private String name;
    private List<Node> children;

    public Node() {
    }

    public Node(String id, String pid, String name) {
        super();
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

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

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<Node> children) {
        this.children = children;
    }

}
