package com.lh.api.product;

import com.lh.entity.CatalogThree;
import com.lh.entity.CatalogTwo;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/9.
 */
public interface ICatalogThreeService {
    List<CatalogThree> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    CatalogThree findById(Integer twoId);

    int add(CatalogThree catalogThree);

    int update(CatalogThree catalogThree);

    void delete(String ids);

    List<CatalogThree> selectThreeBytwoId(Integer twoId);

    List<CatalogThree> getThree();
}
