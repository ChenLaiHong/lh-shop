package com.lh.api.product;

import com.lh.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/1.
 */
public interface IProductService {
    List<Product> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    Product findById(Integer productId);

    int add(Product product);

    int update(Product product);

    void delete(String ids);

    List<Product> getAll();

    Product getById(Map<String, Object> map);
}
