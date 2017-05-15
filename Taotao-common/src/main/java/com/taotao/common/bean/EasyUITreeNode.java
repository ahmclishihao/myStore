package com.taotao.common.bean;

import java.util.List;

/**
 * 通用的easyui节点node
 */
public class EasyUITreeNode {

    private Long id;

    private String text;

    private String state; // 只有closed 和open两种状态

    private List<EasyUITreeNode> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    // state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
    public void setState(boolean state) {
        this.state = state ? "closed" : "open";
    }

    public List<EasyUITreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITreeNode> children) {
        this.children = children;
    }
}
