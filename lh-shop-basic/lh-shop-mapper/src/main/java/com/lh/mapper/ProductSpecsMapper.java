package com.lh.mapper;

import com.lh.entity.ProductSpecs;
import com.lh.entity.ProductSpecsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProductSpecsMapper {
    long countByExample(ProductSpecsExample example);

    int deleteByExample(ProductSpecsExample example);

    int deleteByPrimaryKey(Integer specsId);

    int insert(ProductSpecs record);

    int insertSelective(ProductSpecs record);

    List<ProductSpecs> selectByExample(ProductSpecsExample example);

    ProductSpecs selectByPrimaryKey(Integer specsId);

    int updateByExampleSelective(@Param("record") ProductSpecs record, @Param("example") ProductSpecsExample example);

    int updateByExample(@Param("record") ProductSpecs record, @Param("example") ProductSpecsExample example);

    int updateByPrimaryKeySelective(ProductSpecs record);

    int updateByPrimaryKey(ProductSpecs record);

    List<ProductSpecs> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    void updateList(List list);
}