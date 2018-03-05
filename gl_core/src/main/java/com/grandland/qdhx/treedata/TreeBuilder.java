package com.grandland.qdhx.treedata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class TreeBuilder<T extends INode<T>> {

    List<T> nodes = new ArrayList<T>();

    public String buildTreeJson(List<T> nodes) {

        TreeBuilder<T> treeBuilder = new TreeBuilder<T>(nodes);

        return treeBuilder.buildJSONTree();
    }

    public List<T> buildTree(List<T> nodes) {
        this.nodes = nodes;

        return buildTree();
    }

    public TreeBuilder() {
    }

    public TreeBuilder(List<T> nodes) {
        super();
        this.nodes = nodes;
    }

    // 构建JSON树形结构
    public String buildJSONTree() {
        List<T> nodeTree = buildTree();
        return JSON.toJSONString(nodeTree);
    }

    // 构建树形结构
    public List<T> buildTree() {
        List<T> treeNodes = new ArrayList<>();
        List<T> rootNodes = getRootNodes();
        for (T rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(T node) {
        List<T> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (T child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children);
        }
    }

    // 获取父节点下所有的子节点
    public List<T> getChildNodes(T pnode) {
        List<T> childNodes = new ArrayList<>();
        for (T n : nodes) {
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(T node) {
        boolean isRootNode = false;
        if (node.getPid().equals("#")) {
            isRootNode = true;
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<T> getRootNodes() {
        List<T> rootNodes = new ArrayList<>();
        for (T n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

    /*
     * 在树形图没有孩子节点时，清理掉该父节点
     * 只清理首层节点，要不在清理子层节点时会误清理，造成错误
     * @author loverqi
     * @date 2018年2月8日
     * */

    /**   start     */

    // 构建非空树形结构
    public List<T> buildEmptyTree(List<T> nodes) {
        this.nodes = nodes;

        return buildEmptyTree();
    }

    public List<T> buildEmptyTree() {
        List<T> treeNodes = new ArrayList<>();
        List<T> rootNodes = getRootNodes();
        Iterator<T> iterator = rootNodes.iterator();
        while (iterator.hasNext()) {
            T rootNode = iterator.next();
            boolean empty = buildEmptyChildNodes(rootNode);
            if (empty) {
                treeNodes.add(rootNode);
            } else {
                iterator.remove();
            }

        }
        return treeNodes;
    }

    // 递归子节点
    public boolean buildEmptyChildNodes(T node) {
        List<T> children = getChildNodes(node);
        boolean isEmpty = false;
        if (!children.isEmpty()) {
            for (T child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children);
            isEmpty = true;
        }

        return isEmpty;
    }

    /**   end     */
}
