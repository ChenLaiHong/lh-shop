package com.lh.mapper;

import com.lh.entity.HeadLines;
import com.lh.entity.HeadLinesExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HeadLinesMapper {
    long countByExample(HeadLinesExample example);

    int deleteByExample(HeadLinesExample example);

    int deleteByPrimaryKey(Integer newsId);

    int insert(HeadLines record);

    int insertSelective(HeadLines record);

    List<HeadLines> selectByExampleWithBLOBs(HeadLinesExample example);

    List<HeadLines> selectByExample(HeadLinesExample example);

    HeadLines selectByPrimaryKey(Integer newsId);

    int updateByExampleSelective(@Param("record") HeadLines record, @Param("example") HeadLinesExample example);

    int updateByExampleWithBLOBs(@Param("record") HeadLines record, @Param("example") HeadLinesExample example);

    int updateByExample(@Param("record") HeadLines record, @Param("example") HeadLinesExample example);

    int updateByPrimaryKeySelective(HeadLines record);

    int updateByPrimaryKeyWithBLOBs(HeadLines record);

    int updateByPrimaryKey(HeadLines record);

    List<HeadLines> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    void updateList(List list);

}