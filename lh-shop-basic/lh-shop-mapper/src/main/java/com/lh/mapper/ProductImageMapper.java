package com.lh.mapper;

import com.lh.entity.ProductImage;
import com.lh.entity.ProductImageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProductImageMapper {
    long countByExample(ProductImageExample example);

    int deleteByExample(ProductImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);

    List<ProductImage> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    void updateList(List list);
}