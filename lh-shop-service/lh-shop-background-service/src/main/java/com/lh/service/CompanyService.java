package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.ICompanyService;
import com.lh.entity.ExpressCompany;
import com.lh.entity.ExpressCompanyExample;
import com.lh.entity.ProductImage;
import com.lh.mapper.ExpressCompanyMapper;
import com.lh.mapper.ProductImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/3/22.
 */
@Component
@Service
public class CompanyService implements ICompanyService{
    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;
    @Override
    public List<ExpressCompany> pageList(Map<String, Object> map) {
        return expressCompanyMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return expressCompanyMapper.getTotal(map);
    }

    @Override
    public int add(ExpressCompany company) {
        company.setAddTime(new Date());
        company.setUpdateTime(new Date());
        company.setState("1");
        return expressCompanyMapper.insertSelective(company);
    }

    @Override
    public ExpressCompany findById(Integer companyId) {
        return expressCompanyMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public int update(ExpressCompany company) {
        company.setUpdateTime(new Date());
        return expressCompanyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public void delete(String ids) {
        expressCompanyMapper.updateList(StringIds(ids));
    }

    @Override
    public int updateState(ExpressCompany company) {
        if(company.getState().equals("1")){
            company.setState("0");
        }else {
            company.setState("1");
        }
       return expressCompanyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public List<ExpressCompany> getAll() {
        ExpressCompanyExample companyExample = new ExpressCompanyExample();
        companyExample.createCriteria().andStateEqualTo("1");
        return expressCompanyMapper.selectByExample(companyExample);
    }
}
