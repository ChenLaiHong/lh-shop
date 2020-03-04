package com.lh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductSpecs implements Serializable {
    private Integer specsId;

    private String specsName;

    private BigDecimal specsPrice;

    private Integer specsStock;

    //额外添加的新库存
    private Integer newSpecsStock;

    private Integer productId;

    private Integer state;

    private Date updateTime;

    private Product product;

    public Integer getSpecsId() {
        return specsId;
    }

    public void setSpecsId(Integer specsId) {
        this.specsId = specsId;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName == null ? null : specsName.trim();
    }

    public BigDecimal getSpecsPrice() {
        return specsPrice;
    }

    public void setSpecsPrice(BigDecimal specsPrice) {
        this.specsPrice = specsPrice;
    }

    public Integer getSpecsStock() {
        return specsStock;
    }

    public void setSpecsStock(Integer specsStock) {
        this.specsStock = specsStock;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNewSpecsStock() {
        return newSpecsStock;
    }

    public void setNewSpecsStock(Integer newSpecsStock) {
        this.newSpecsStock = newSpecsStock;
    }

    public ProductSpecs() {
    }

    public ProductSpecs(Integer specsId, String specsName, BigDecimal specsPrice, Integer specsStock, Integer newSpecsStock, Integer productId, Integer state, Date updateTime, Product product) {
        this.specsId = specsId;
        this.specsName = specsName;
        this.specsPrice = specsPrice;
        this.specsStock = specsStock;
        this.newSpecsStock = newSpecsStock;
        this.productId = productId;
        this.state = state;
        this.updateTime = updateTime;
        this.product = product;
    }
}