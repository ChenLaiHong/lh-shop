package com.lh.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItems implements Serializable {
    private Integer id;

    private String productName;

    private String producrIcon;

    private Integer productNum;

    private BigDecimal productPrice;

    private Integer orderId;

    private OrderBasics orderBasics;

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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderBasics getOrderBasics() {
        return orderBasics;
    }

    public void setOrderBasics(OrderBasics orderBasics) {
        this.orderBasics = orderBasics;
    }

    public OrderItems() {
    }

    public OrderItems(Integer id, String productName, String producrIcon, Integer productNum, BigDecimal productPrice, Integer orderId, OrderBasics orderBasics) {
        this.id = id;
        this.productName = productName;
        this.producrIcon = producrIcon;
        this.productNum = productNum;
        this.productPrice = productPrice;
        this.orderId = orderId;
        this.orderBasics = orderBasics;
    }
}