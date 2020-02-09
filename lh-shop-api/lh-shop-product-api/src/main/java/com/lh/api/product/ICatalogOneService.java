package com.lh.api.product;

import com.lh.entity.CatalogOne;
import com.lh.shop.common.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/4.
 */
public interface ICatalogOneService  {
    List<CatalogOne> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int add(CatalogOne catalogOne);

    int update(CatalogOne catalogOne);

    CatalogOne findById(Integer oneId);

    void delete(String ids);

    List<CatalogOne> getAll();
}
