package com.lh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable {
    private Integer productId;

    private String productName;

    private String productOneImage;

    private String images;

    private BigDecimal shopPrice;

    private Integer productState;

    private Date updateTime;

    private Integer threeId;

    //数据库没有此字段，临时存储新的图片地址
    private String newImages;

    private String productDetail;

    private CatalogThree catalogThree;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductOneImage() {
        return productOneImage;
    }

    public void setProductOneImage(String productOneImage) {
        this.productOneImage = productOneImage == null ? null : productOneImage.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getThreeId() {
        return threeId;
    }

    public void setThreeId(Integer threeId) {
        this.threeId = threeId;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail == null ? null : productDetail.trim();
    }

    public CatalogThree getCatalogThree() {
        return catalogThree;
    }

    public void setCatalogThree(CatalogThree catalogThree) {
        this.catalogThree = catalogThree;
    }

    public String getNewImages() {
        return newImages;
    }

    public void setNewImages(String newImages) {
        this.newImages = newImages;
    }

    public Product() {
    }

    public Product(Integer productId, String productName, String productOneImage, String images, BigDecimal shopPrice, Integer productState, Date updateTime, Integer threeId, String newImages, String productDetail, CatalogThree catalogThree) {
        this.productId = productId;
        this.productName = productName;
        this.productOneImage = productOneImage;
        this.images = images;
        this.shopPrice = shopPrice;
        this.productState = productState;
        this.updateTime = updateTime;
        this.threeId = threeId;
        this.newImages = newImages;
        this.productDetail = productDetail;
        this.catalogThree = catalogThree;
    }
}