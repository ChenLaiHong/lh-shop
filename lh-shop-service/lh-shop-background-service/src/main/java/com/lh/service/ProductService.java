package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lh.api.product.IProductService;
import com.lh.api.vo.ProductVO;
import com.lh.entity.Product;
import com.lh.entity.ProductExample;
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
    public int add(ProductVO productVO) {
        Product product = productVO.getProduct();
        product.setUpdateTime(new Date());
        productMapper.insertSelective(product);
        return product.getProductId();
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

    @Override
    public PageInfo<Product> getAll(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);

        List<Product> list = productMapper.list();
        PageInfo<Product> pageInfo = new PageInfo<Product>(list,3);
        return pageInfo;
    }

    @Override
    public Product getById(Map<String, Object> map) {
        return productMapper.getById(map);
    }

    @Override
    public List<Product> getProductList() {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductStateEqualTo(1);
        return productMapper.selectByExample(productExample);
    }

    @Override
    public Product getOneProduct(String s) {
        return productMapper.selectByPrimaryKey(Integer.valueOf(s));
    }

    @Override
    public List<Product> getByPids(List<Integer> productIds) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductIdIn(productIds);
        return productMapper.selectByExample(productExample);
    }
}
