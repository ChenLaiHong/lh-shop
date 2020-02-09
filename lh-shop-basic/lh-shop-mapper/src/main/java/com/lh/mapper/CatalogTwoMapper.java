package com.lh.mapper;

import com.lh.entity.CatalogTwo;
import com.lh.entity.CatalogTwoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CatalogTwoMapper {
    long countByExample(CatalogTwoExample example);

    int deleteByExample(CatalogTwoExample example);

    int deleteByPrimaryKey(Integer twoId);

    int insert(CatalogTwo record);

    int insertSelective(CatalogTwo record);

    List<CatalogTwo> selectByExample(CatalogTwoExample example);

    CatalogTwo selectByPrimaryKey(Integer twoId);

    int updateByExampleSelective(@Param("record") CatalogTwo record, @Param("example") CatalogTwoExample example);

    int updateByExample(@Param("record") CatalogTwo record, @Param("example") CatalogTwoExample example);

    int updateByPrimaryKeySelective(CatalogTwo record);

    int updateByPrimaryKey(CatalogTwo record);

    List<CatalogTwo> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    void updateList(List list);
}