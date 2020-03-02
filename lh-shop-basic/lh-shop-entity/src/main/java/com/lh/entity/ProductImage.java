package com.lh.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductImage implements Serializable {
    private Integer id;

    private Long productId;

    private String imageName;

    private String imageUrl;

    private Integer state;

    private Date updateTime;

    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
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

    public ProductImage() {
    }

    public ProductImage(Integer id, Long productId, String imageName, String imageUrl, Integer state, Date updateTime, Product product) {
        this.id = id;
        this.productId = productId;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.state = state;
        this.updateTime = updateTime;
        this.product = product;
    }
}