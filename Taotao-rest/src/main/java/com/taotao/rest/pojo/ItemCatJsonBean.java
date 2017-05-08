package com.taotao.rest.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemCatJsonBean {

    @JsonProperty("u")
    private String url;
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    // 不适用泛型为了满足不同的item
    private List<Object> items;

    @JsonProperty("data")
    private List<Object> data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
