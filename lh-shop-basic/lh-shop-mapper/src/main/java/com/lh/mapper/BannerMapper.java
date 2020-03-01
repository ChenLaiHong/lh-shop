package com.lh.mapper;

import com.lh.entity.Banner;
import com.lh.entity.BannerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BannerMapper {
    long countByExample(BannerExample example);

    int deleteByExample(BannerExample example);

    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    List<Banner> selectByExample(BannerExample example);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByExampleSelective(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByExample(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    List<Banner> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    void updateList(List list);
}