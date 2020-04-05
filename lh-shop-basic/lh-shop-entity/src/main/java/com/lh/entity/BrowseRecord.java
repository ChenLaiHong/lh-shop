package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class BrowseRecord implements Serializable {
    private Integer id;

    private Integer productId;

    private String browseTime;

    private Integer frequency;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(String browseTime) {
        this.browseTime = browseTime == null ? null : browseTime.trim();
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}