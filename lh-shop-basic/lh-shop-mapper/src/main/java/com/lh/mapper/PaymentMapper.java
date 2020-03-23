package com.lh.mapper;

import com.lh.entity.Payment;
import com.lh.entity.PaymentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    long countByExample(PaymentExample example);

    int deleteByExample(PaymentExample example);

    int deleteByPrimaryKey(Integer payId);

    int insert(Payment record);

    int insertSelective(Payment record);

    List<Payment> selectByExample(PaymentExample example);

    Payment selectByPrimaryKey(Integer payId);

    int updateByExampleSelective(@Param("record") Payment record, @Param("example") PaymentExample example);

    int updateByExample(@Param("record") Payment record, @Param("example") PaymentExample example);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    List<Payment> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int updateList(List list);
}