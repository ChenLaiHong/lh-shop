package com.lh.mapper;

import com.lh.entity.ProductCollect;
import com.lh.entity.ProductCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCollectMapper {
    long countByExample(ProductCollectExample example);

    int deleteByExample(ProductCollectExample example);

    int deleteByPrimaryKey(Integer collectId);

    int insert(ProductCollect record);

    int insertSelective(ProductCollect record);

    List<ProductCollect> selectByExample(ProductCollectExample example);

    ProductCollect selectByPrimaryKey(Integer collectId);

    int updateByExampleSelective(@Param("record") ProductCollect record, @Param("example") ProductCollectExample example);

    int updateByExample(@Param("record") ProductCollect record, @Param("example") ProductCollectExample example);

    int updateByPrimaryKeySelective(ProductCollect record);

    int updateByPrimaryKey(ProductCollect record);
}