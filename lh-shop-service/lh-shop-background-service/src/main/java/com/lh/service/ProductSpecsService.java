package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IProductSpecsService;
import com.lh.entity.ProductSpecs;
import com.lh.mapper.ProductSpecsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/3/3.
 */
@Component
@Service
public class ProductSpecsService implements IProductSpecsService {
    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Override
    public List<ProductSpecs> pageList(Map<String, Object> map) {
        return productSpecsMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return productSpecsMapper.getTotal(map);
    }

    @Override
    public int add(ProductSpecs productSpecs) {
        productSpecs.setState(1);
        productSpecs.setUpdateTime(new Date());
        return productSpecsMapper.insertSelective(productSpecs);
    }

    @Override
    public ProductSpecs findById(Integer specsId) {
        return productSpecsMapper.selectByPrimaryKey(specsId);
    }

    @Override
    public int update(ProductSpecs productSpecs) {

        System.out.println(productSpecs.getNewSpecsStock()+"新的库存。。。");
        productSpecs.setUpdateTime(new Date());
        if(productSpecs.getNewSpecsStock() != null){
            productSpecs.setSpecsStock(productSpecs.getNewSpecsStock()+productSpecsMapper.selectByPrimaryKey(productSpecs.getSpecsId()).getSpecsStock());
        }
        return productSpecsMapper.updateByPrimaryKeySelective(productSpecs);
    }

    @Override
    public void delete(String ids) {
        productSpecsMapper.updateList(StringIds(ids));
    }
}
