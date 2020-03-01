package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.ICatalogTwoService;
import com.lh.entity.CatalogTwo;
import com.lh.mapper.CatalogTwoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/2/9.
 */
@Component
@Service
public class CatalogTwoService implements ICatalogTwoService{
    @Autowired
    private CatalogTwoMapper catalogTwoMapper;

    @Override
    public List<CatalogTwo> pageList(Map<String, Object> map) {
        return catalogTwoMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return catalogTwoMapper.getTotal(map);
    }

    @Override
    public CatalogTwo findById(Integer twoId) {
        return catalogTwoMapper.selectByPrimaryKey(twoId);
    }

    @Override
    public int add(CatalogTwo catalogTwo) {
        catalogTwo.setUpdateTime(new Date());
        return catalogTwoMapper.insertSelective(catalogTwo);
    }

    @Override
    public int update(CatalogTwo catalogTwo) {
        catalogTwo.setUpdateTime(new Date());
        return catalogTwoMapper.updateByPrimaryKeySelective(catalogTwo);
    }

    @Override
    public void delete(String ids) {
        catalogTwoMapper.updateList(StringIds(ids));
    }
}
