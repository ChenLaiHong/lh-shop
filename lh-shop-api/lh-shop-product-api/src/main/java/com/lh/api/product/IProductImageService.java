package com.lh.api.product;

import com.lh.entity.ProductImage;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/2.
 */
public interface IProductImageService {
    List<ProductImage> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int add(ProductImage productImage);

    ProductImage findById(Integer id);

    int update(ProductImage productImage);

    void delete(String ids);
}
