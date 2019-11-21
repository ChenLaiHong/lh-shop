package com.lh.entity;

import java.io.Serializable;
import java.util.List;

public class ProductType implements Serializable{
    private Long id;

    private String name;

    private Integer isLeaf;

    private Long parentid;

    private Integer typeLevels;

    private List<ProductType> productTypeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Integer getTypeLevels() {
        return typeLevels;
    }

    public void setTypeLevels(Integer typeLevels) {
        this.typeLevels = typeLevels;
    }

    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }
}