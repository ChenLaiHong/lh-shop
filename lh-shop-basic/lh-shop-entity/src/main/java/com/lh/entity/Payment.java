package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private Integer payId;

    private String payName;

    private String payIcon;

    private Date addTime;

    private Date updateTime;

    private String state;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public String getPayIcon() {
        return payIcon;
    }

    public void setPayIcon(String payIcon) {
        this.payIcon = payIcon == null ? null : payIcon.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}