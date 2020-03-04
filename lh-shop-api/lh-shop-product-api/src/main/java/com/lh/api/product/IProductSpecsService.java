package com.lh.api.product;

import com.lh.entity.ProductSpecs;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/3.
 */
public interface IProductSpecsService {
    List<ProductSpecs> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int add(ProductSpecs productSpecs);

    ProductSpecs findById(Integer specsId);

    int update(ProductSpecs productSpecs);

    void delete(String ids);
}
