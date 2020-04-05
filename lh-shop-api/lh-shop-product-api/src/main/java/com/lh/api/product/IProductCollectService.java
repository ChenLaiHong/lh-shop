package com.lh.api.product;

import com.lh.entity.Product;

import java.util.List;

/**
 * Created by laiHom on 2020/4/5.
 */
public interface IProductCollectService {
    int add(Integer userId, Integer productId);

    List<Product> findByUserId(Integer userId);

    void findCollect(Integer userId, Integer productId);
}
