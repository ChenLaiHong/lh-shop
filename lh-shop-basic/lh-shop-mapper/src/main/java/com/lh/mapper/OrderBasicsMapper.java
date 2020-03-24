package com.lh.mapper;

import com.lh.entity.OrderBasics;
import com.lh.entity.OrderBasicsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderBasicsMapper {
    long countByExample(OrderBasicsExample example);

    int deleteByExample(OrderBasicsExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderBasics record);

    int insertSelective(OrderBasics record);

    List<OrderBasics> selectByExample(OrderBasicsExample example);

    OrderBasics selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") OrderBasics record, @Param("example") OrderBasicsExample example);

    int updateByExample(@Param("record") OrderBasics record, @Param("example") OrderBasicsExample example);

    int updateByPrimaryKeySelective(OrderBasics record);

    int updateByPrimaryKey(OrderBasics record);

    void insertAndGetId(OrderBasics orderBasics);

    List<OrderBasics> getAll(Map<String, Object> map);

    List<OrderBasics> getAllNoPay(Map<String, Object> map);
}