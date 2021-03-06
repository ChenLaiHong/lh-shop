package com.lh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBasics implements Serializable {
    private Integer orderId;

    private String orderNumber;

    private Integer userId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private BigDecimal totalMoney;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date orderTime;

    private Integer payId;

    private Integer companyId;

    private String state;

    private String remarks;

    private Person person;

    private Payment payment;

    private ExpressCompany company;

    private List<OrderItems> orderItemsList;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ExpressCompany getCompany() {
        return company;
    }

    public void setCompany(ExpressCompany company) {
        this.company = company;
    }

    public OrderBasics() {
    }

    public OrderBasics(Integer orderId, String orderNumber, Integer userId, String receiverName, String receiverPhone, String receiverAddress, BigDecimal totalMoney, Date orderTime, Integer payId, Integer companyId, String state, String remarks, Person person, Payment payment, ExpressCompany company, List<OrderItems> orderItemsList) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.totalMoney = totalMoney;
        this.orderTime = orderTime;
        this.payId = payId;
        this.companyId = companyId;
        this.state = state;
        this.remarks = remarks;
        this.person = person;
        this.payment = payment;
        this.company = company;
        this.orderItemsList = orderItemsList;
    }
}