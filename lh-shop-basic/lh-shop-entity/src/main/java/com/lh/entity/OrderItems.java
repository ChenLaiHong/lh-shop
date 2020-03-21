package com.lh.entity;

import java.io.Serializable;

public class OrderItems implements Serializable {
    private Integer id;

    private String productName;

    private String producrIcon;

    private Integer productNum;

    private String orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProducrIcon() {
        return producrIcon;
    }

    public void setProducrIcon(String producrIcon) {
        this.producrIcon = producrIcon == null ? null : producrIcon.trim();
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }
}