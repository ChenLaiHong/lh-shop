package com.lh.api.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by laiHom on 2020/3/18.
 * 购物车项
 */
public class CartItem implements Serializable {

    private Integer productId;
    private Integer count;
    private Date updateTime;

    public CartItem(Integer productId, Integer count, Date updateTime) {
        this.productId = productId;
        this.count = count;
        this.updateTime = updateTime;
    }

    public CartItem() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
