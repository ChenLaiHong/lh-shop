package com.lh.api.product;

import com.lh.entity.CatalogTwo;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/9.
 */
public interface ICatalogTwoService {
    List<CatalogTwo> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    CatalogTwo findById(Integer twoId);

    int add(CatalogTwo catalogTwo);

    int update(CatalogTwo catalogTwo);

    void delete(String ids);

    List<CatalogTwo> selectTwoByOneId(Integer oneId);

    List<CatalogTwo> getTwo();
}
