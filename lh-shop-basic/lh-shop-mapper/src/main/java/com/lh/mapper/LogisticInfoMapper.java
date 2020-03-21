package com.lh.mapper;

import com.lh.entity.LogisticInfo;
import com.lh.entity.LogisticInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticInfoMapper {
    long countByExample(LogisticInfoExample example);

    int deleteByExample(LogisticInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogisticInfo record);

    int insertSelective(LogisticInfo record);

    List<LogisticInfo> selectByExample(LogisticInfoExample example);

    LogisticInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogisticInfo record, @Param("example") LogisticInfoExample example);

    int updateByExample(@Param("record") LogisticInfo record, @Param("example") LogisticInfoExample example);

    int updateByPrimaryKeySelective(LogisticInfo record);

    int updateByPrimaryKey(LogisticInfo record);
}