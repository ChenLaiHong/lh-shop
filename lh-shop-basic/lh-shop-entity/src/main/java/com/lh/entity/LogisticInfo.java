package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class LogisticInfo implements Serializable {
    private Integer id;

    private String companyName;

    private String orderNumber;

    private Integer courierNumber;

    private Date logisticTime;

    private String logisticPlace;

    private String logisticExplain;

    private String logisticState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Integer getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(Integer courierNumber) {
        this.courierNumber = courierNumber;
    }

    public Date getLogisticTime() {
        return logisticTime;
    }

    public void setLogisticTime(Date logisticTime) {
        this.logisticTime = logisticTime;
    }

    public String getLogisticPlace() {
        return logisticPlace;
    }

    public void setLogisticPlace(String logisticPlace) {
        this.logisticPlace = logisticPlace == null ? null : logisticPlace.trim();
    }

    public String getLogisticExplain() {
        return logisticExplain;
    }

    public void setLogisticExplain(String logisticExplain) {
        this.logisticExplain = logisticExplain == null ? null : logisticExplain.trim();
    }

    public String getLogisticState() {
        return logisticState;
    }

    public void setLogisticState(String logisticState) {
        this.logisticState = logisticState == null ? null : logisticState.trim();
    }
}