package com.lh.mapper;

import com.lh.entity.CatalogOne;
import com.lh.entity.CatalogOneExample;
import java.util.List;
import java.util.Map;

import com.lh.entity.CatalogThree;
import com.lh.shop.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface CatalogOneMapper  {

    int deleteByPrimaryKey(Integer threeId);

    int insert(CatalogOne record);

    int insertSelective(CatalogOne record);

    CatalogOne selectByPrimaryKey(Integer threeId);

    int updateByPrimaryKeySelective(CatalogOne record);

    int updateByPrimaryKey(CatalogOne record);

    long countByExample(CatalogOneExample example);

    int deleteByExample(CatalogOneExample example);

    List<CatalogOne> selectByExample(CatalogOneExample example);

    int updateByExampleSelective(@Param("record") CatalogOne record, @Param("example") CatalogOneExample example);

    int updateByExample(@Param("record") CatalogOne record, @Param("example") CatalogOneExample example);

    List<CatalogOne> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    void updateList(List ints);
}