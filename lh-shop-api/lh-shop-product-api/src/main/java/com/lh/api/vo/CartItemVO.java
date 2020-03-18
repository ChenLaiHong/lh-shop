package com.lh.api.vo;

import com.lh.entity.ProductSpecs;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by laiHom on 2020/3/18.
 * 用于页面展示
 */
public class CartItemVO implements Serializable {
    private ProductSpecs productSpecs;
    private Integer count;
    private Date updateTime;

    public ProductSpecs getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(ProductSpecs productSpecs) {
        this.productSpecs = productSpecs;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
