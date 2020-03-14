package com.lh.api.vo;

import com.lh.entity.Product;

import java.io.Serializable;

/**
 * Created by laiHom on 2020/3/11.
 */
public class ProductVO implements Serializable {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
