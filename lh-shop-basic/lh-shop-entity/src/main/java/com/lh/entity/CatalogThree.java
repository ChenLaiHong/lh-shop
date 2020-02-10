package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class CatalogThree implements Serializable {
    private Integer threeId;

    private String threeName;

    private Date updateTime;

    private Integer state;

    private Integer twoId;

    private CatalogTwo catalogTwo;

    public Integer getThreeId() {
        return threeId;
    }

    public void setThreeId(Integer threeId) {
        this.threeId = threeId;
    }

    public String getThreeName() {
        return threeName;
    }

    public void setThreeName(String threeName) {
        this.threeName = threeName == null ? null : threeName.trim();
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

    public Integer getTwoId() {
        return twoId;
    }

    public void setTwoId(Integer twoId) {
        this.twoId = twoId;
    }

    public CatalogTwo getCatalogTwo() {
        return catalogTwo;
    }

    public void setCatalogTwo(CatalogTwo catalogTwo) {
        this.catalogTwo = catalogTwo;
    }

    public CatalogThree() {
    }

    public CatalogThree(Integer threeId, String threeName, Date updateTime, Integer state, Integer twoId, CatalogTwo catalogTwo) {
        this.threeId = threeId;
        this.threeName = threeName;
        this.updateTime = updateTime;
        this.state = state;
        this.twoId = twoId;
        this.catalogTwo = catalogTwo;
    }
}