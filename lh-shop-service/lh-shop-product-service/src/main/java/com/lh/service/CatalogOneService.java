package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.ICatalogOneService;
import com.lh.entity.CatalogOne;
import com.lh.entity.CatalogOneExample;
import com.lh.mapper.CatalogOneMapper;
import com.lh.shop.common.base.BaseDao;
import com.lh.shop.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/2/4.
 */
@Component
@Service
public class CatalogOneService  implements ICatalogOneService {


    @Autowired
    private CatalogOneMapper catalogOneMapper;

    @Override
    public List<CatalogOne> pageList(Map<String, Object> map) {
        return catalogOneMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return catalogOneMapper.getTotal(map);
    }

    @Override
    public int add(CatalogOne catalogOne) {
        catalogOne.setUpdateTime(new Date());
        catalogOne.setState(1);
        return catalogOneMapper.insertSelective(catalogOne);
    }

    @Override
    public int update(CatalogOne catalogOne) {
        catalogOne.setUpdateTime(new Date());
        return catalogOneMapper.updateByPrimaryKeySelective(catalogOne);
    }

    @Override
    public CatalogOne findById(Integer oneId) {
        return catalogOneMapper.selectByPrimaryKey(oneId);
    }

    @Override
    public void delete(String ids) {
        catalogOneMapper.updateList(StringIds(ids));
    }

    @Override
    public List<CatalogOne> getAll() {
        CatalogOneExample catalogOneExample = new CatalogOneExample();
        catalogOneExample.createCriteria().andStateEqualTo(1);
        return catalogOneMapper.selectByExample(catalogOneExample);
    }
}
