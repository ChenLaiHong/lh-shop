package com.lh.mapper;

import com.lh.entity.ExpressCompany;
import com.lh.entity.ExpressCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressCompanyMapper {
    long countByExample(ExpressCompanyExample example);

    int deleteByExample(ExpressCompanyExample example);

    int deleteByPrimaryKey(Integer companyId);

    int insert(ExpressCompany record);

    int insertSelective(ExpressCompany record);

    List<ExpressCompany> selectByExample(ExpressCompanyExample example);

    ExpressCompany selectByPrimaryKey(Integer companyId);

    int updateByExampleSelective(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    int updateByExample(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    int updateByPrimaryKeySelective(ExpressCompany record);

    int updateByPrimaryKey(ExpressCompany record);
}