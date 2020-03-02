package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.ICatalogThreeService;
import com.lh.api.product.ICatalogTwoService;
import com.lh.entity.CatalogThree;
import com.lh.entity.CatalogThreeExample;
import com.lh.entity.CatalogTwo;
import com.lh.mapper.CatalogThreeMapper;
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
public class CatalogThreeService implements ICatalogThreeService {
    @Autowired
    private CatalogThreeMapper catalogThreeMapper;

    @Override
    public List<CatalogThree> pageList(Map<String, Object> map) {
        return catalogThreeMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return catalogThreeMapper.getTotal(map);
    }

    @Override
    public CatalogThree findById(Integer twoId) {
        return catalogThreeMapper.selectByPrimaryKey(twoId);
    }

    @Override
    public int add(CatalogThree catalogThree) {
        catalogThree.setUpdateTime(new Date());
        return catalogThreeMapper.insertSelective(catalogThree);
    }

    @Override
    public int update(CatalogThree catalogThree) {
        catalogThree.setUpdateTime(new Date());
        return catalogThreeMapper.updateByPrimaryKeySelective(catalogThree);
    }

    @Override
    public void delete(String ids) {
        catalogThreeMapper.updateList(StringIds(ids));
    }

    @Override
    public List<CatalogThree> selectThreeBytwoId(Integer twoId) {
        return catalogThreeMapper.selectByTwoId(twoId);
    }

    @Override
    public List<CatalogThree> getThree() {
        CatalogThreeExample catalogThreeExample = new CatalogThreeExample();
        catalogThreeExample.createCriteria().andStateEqualTo(1);
        return catalogThreeMapper.selectByExample(catalogThreeExample);
    }
}
