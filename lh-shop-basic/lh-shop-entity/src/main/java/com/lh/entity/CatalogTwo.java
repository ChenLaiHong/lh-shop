package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class CatalogTwo implements Serializable {
    private Integer twoId;

    private String twoName;

    private Date updateTime;

    private Integer state;

    private Integer oneId;

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
}