package com.lh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CatalogTwo implements Serializable {
    private Integer twoId;

    private String twoName;

    private Date updateTime;

    private Integer state;

    private Integer oneId;

    private CatalogOne catalogOne;

    private List<CatalogThree> catalogThreeList;

    public Integer getTwoId() {
        return twoId;
    }

    public void setTwoId(Integer twoId) {
        this.twoId = twoId;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName == null ? null : twoName.trim();
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

    public Integer getOneId() {
        return oneId;
    }

    public void setOneId(Integer oneId) {
        this.oneId = oneId;
    }

    public CatalogOne getCatalogOne() {
        return catalogOne;
    }

    public void setCatalogOne(CatalogOne catalogOne) {
        this.catalogOne = catalogOne;
    }

    public List<CatalogThree> getCatalogThreeList() {
        return catalogThreeList;
    }

    public void setCatalogThreeList(List<CatalogThree> catalogThreeList) {
        this.catalogThreeList = catalogThreeList;
    }

    public CatalogTwo() {
    }

    public CatalogTwo(Integer twoId, String twoName, Date updateTime, Integer state, Integer oneId, CatalogOne catalogOne, List<CatalogThree> catalogThreeList) {
        this.twoId = twoId;
        this.twoName = twoName;
        this.updateTime = updateTime;
        this.state = state;
        this.oneId = oneId;
        this.catalogOne = catalogOne;
        this.catalogThreeList = catalogThreeList;
    }
}