package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class CatalogOne implements Serializable {
    private Integer oneId;

    private String oneName;

    private String oneImage;

    private Date updateTime;

    private Integer state;

    public Integer getOneId() {
        return oneId;
    }

    public void setOneId(Integer oneId) {
        this.oneId = oneId;
    }

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName == null ? null : oneName.trim();
    }

    public String getOneImage() {
        return oneImage;
    }

    public void setOneImage(String oneImage) {
        this.oneImage = oneImage == null ? null : oneImage.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}