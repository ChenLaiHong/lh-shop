package com.lh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CatalogOne implements Serializable {
    private Integer oneId;

    private String oneName;

    private String oneImage;

    private Date updateTime;

    private Integer state;

    private List<CatalogTwo> catalogTwoList;

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

    public List<CatalogTwo> getCatalogTwoList() {
        return catalogTwoList;
    }

    public void setCatalogTwoList(List<CatalogTwo> catalogTwoList) {
        this.catalogTwoList = catalogTwoList;
    }

    public CatalogOne() {
    }

    public CatalogOne(Integer oneId, String oneName, String oneImage, Date updateTime, Integer state, List<CatalogTwo> catalogTwoList) {
        this.oneId = oneId;
        this.oneName = oneName;
        this.oneImage = oneImage;
        this.updateTime = updateTime;
        this.state = state;
        this.catalogTwoList = catalogTwoList;
    }
}