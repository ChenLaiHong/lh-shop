package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable {
    private Integer addressId;

    private String receiver;

    private String phone;

    private String location;

    private String fullAddress;

    private Integer defaultAddress;

    private Date addTime;

    private Date updateTime;

    private Integer state;

    private Integer userId;

    private Person person;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress == null ? null : fullAddress.trim();
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address() {
    }

    public Address(Integer addressId, String receiver, String phone, String location, String fullAddress, Integer defaultAddress, Date addTime, Date updateTime, Integer state, Integer userId, Person person) {
        this.addressId = addressId;
        this.receiver = receiver;
        this.phone = phone;
        this.location = location;
        this.fullAddress = fullAddress;
        this.defaultAddress = defaultAddress;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.state = state;
        this.userId = userId;
        this.person = person;
    }
}