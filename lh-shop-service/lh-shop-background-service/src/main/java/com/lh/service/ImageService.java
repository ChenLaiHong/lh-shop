package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IProductImageService;
import com.lh.entity.ProductImage;
import com.lh.mapper.ProductImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/3/2.
 */
@Component
@Service
public class ImageService implements IProductImageService {

    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public List<ProductImage> pageList(Map<String, Object> map) {
        return productImageMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return productImageMapper.getTotal(map);
    }

    @Override
    public int add(ProductImage productImage) {
        productImage.setUpdateTime(new Date());
        return productImageMapper.insertSelective(productImage);
    }

    @Override
    public ProductImage findById(Integer id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(ProductImage productImage) {
        productImage.setUpdateTime(new Date());
        return productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public void delete(String ids) {

        productImageMapper.updateList(StringIds(ids));
    }
}
