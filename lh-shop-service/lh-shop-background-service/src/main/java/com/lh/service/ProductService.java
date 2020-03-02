package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lh.api.product.IProductService;
import com.lh.entity.Product;
import com.lh.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;
import static com.lh.shop.common.util.StringUtil.findPath;

/**
 * Created by laiHom on 2020/3/1.
 */
@Component
@Service
public class ProductService implements IProductService {


    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> pageList(Map<String, Object> map) {
        return productMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return productMapper.getTotal(map);
    }

    @Override
    public Product findById(Integer productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public int add(Product product) {
        product.setUpdateTime(new Date());
        return productMapper.insertSelective(product);
    }

    @Override
    public int update(Product product) {

        product.setImages(product.getNewImages());
        product.setUpdateTime(new Date());
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void delete(String ids) {
        productMapper.updateList(StringIds(ids));
    }
}
