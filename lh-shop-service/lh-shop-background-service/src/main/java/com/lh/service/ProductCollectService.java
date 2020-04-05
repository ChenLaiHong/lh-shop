package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IProductCollectService;
import com.lh.entity.Product;
import com.lh.entity.ProductCollect;
import com.lh.entity.ProductCollectExample;
import com.lh.mapper.ProductCollectMapper;
import com.lh.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laiHom on 2020/4/5.
 */
@Component
@Service
public class ProductCollectService implements IProductCollectService {
    @Autowired
    private ProductCollectMapper productCollectMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int add(Integer userId, Integer productId) {

        ProductCollectExample productCollectExample = new ProductCollectExample();
        productCollectExample.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
        List<ProductCollect> collectList = productCollectMapper.selectByExample(productCollectExample);
        int result = 0;
        //首先查看用户是否已经收藏过此商品
        if(collectList.isEmpty()){
            //用户还没有收藏过此商品
            ProductCollect productCollect = new ProductCollect();
            productCollect.setAddTime(new Date());
            productCollect.setProductId(productId);
            productCollect.setUserId(userId);
            productCollect.setState(1);
            result = productCollectMapper.insertSelective(productCollect);
        }
        return result;
    }

    @Override
    public List<Product> findByUserId(Integer userId) {
        List<Product> productList = new ArrayList<>();
        ProductCollectExample productCollectExample = new ProductCollectExample();
        productCollectExample.createCriteria().andUserIdEqualTo(userId);
        List<ProductCollect> collectList = productCollectMapper.selectByExample(productCollectExample);
        if(!collectList.isEmpty()) {
            for (int i = 0; i < collectList.size(); i++) {
                productList.add(productMapper.selectByPrimaryKey(collectList.get(i).getProductId()));
            }
        }
        return productList;
    }

    @Override
    public void findCollect(Integer userId, Integer productId) {
        ProductCollectExample productCollectExample = new ProductCollectExample();
        productCollectExample.createCriteria().andUserIdEqualTo(userId).andProductIdEqualTo(productId);
        List<ProductCollect> collectList = productCollectMapper.selectByExample(productCollectExample);
        if(!collectList.isEmpty()){
            int collectId = collectList.get(0).getCollectId();
            productCollectMapper.deleteByPrimaryKey(collectId);
        }
    }
}
