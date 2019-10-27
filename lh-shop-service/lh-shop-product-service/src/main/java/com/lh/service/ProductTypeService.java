package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IProductTypeService;
import com.lh.entity.ProductType;
import com.lh.mapper.ProductTypeMapper;
import com.lh.shop.common.base.BaseDao;
import com.lh.shop.common.base.BaseServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by laiHom on 2019/10/26.
 */
@Component
@Service
public class ProductTypeService extends BaseServiceImpl<ProductType> implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public BaseDao<ProductType> getBaseDao() {
        return productTypeMapper;
    }


}
