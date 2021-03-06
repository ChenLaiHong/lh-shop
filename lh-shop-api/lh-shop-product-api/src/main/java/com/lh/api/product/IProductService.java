package com.lh.api.product;

import com.github.pagehelper.PageInfo;
import com.lh.api.vo.ProductVO;
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

    int add(ProductVO product);

    int update(Product product);

    void delete(String ids);

    PageInfo<Product> getAll(Integer pageIndex, Integer pageSize);

    Product getById(Map<String, Object> map);

    List<Product> getProductList();

    Product getOneProduct(String s);

    List<Product> getByPids(List<Integer> productIds);
}
