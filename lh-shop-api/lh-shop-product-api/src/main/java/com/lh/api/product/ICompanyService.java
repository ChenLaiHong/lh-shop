package com.lh.api.product;

import com.lh.entity.ExpressCompany;
import com.lh.entity.ProductImage;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/22.
 */
public interface ICompanyService {
    List<ExpressCompany> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int add(ExpressCompany company);

    ExpressCompany findById(Integer companyId);

    int update(ExpressCompany company);

    void delete(String ids);

    int updateState(ExpressCompany company);

    List<ExpressCompany> getAll();
}
