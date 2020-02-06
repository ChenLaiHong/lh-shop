package com.lh.mapper;

import com.lh.entity.CatalogThree;
import com.lh.entity.CatalogThreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CatalogThreeMapper {
    long countByExample(CatalogThreeExample example);

    int deleteByExample(CatalogThreeExample example);

    int deleteByPrimaryKey(Integer threeId);

    int insert(CatalogThree record);

    int insertSelective(CatalogThree record);

    List<CatalogThree> selectByExample(CatalogThreeExample example);

    CatalogThree selectByPrimaryKey(Integer threeId);

    int updateByExampleSelective(@Param("record") CatalogThree record, @Param("example") CatalogThreeExample example);

    int updateByExample(@Param("record") CatalogThree record, @Param("example") CatalogThreeExample example);

    int updateByPrimaryKeySelective(CatalogThree record);

    int updateByPrimaryKey(CatalogThree record);
}