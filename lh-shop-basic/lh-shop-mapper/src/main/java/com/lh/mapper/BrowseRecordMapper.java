package com.lh.mapper;

import com.lh.entity.BrowseRecord;
import com.lh.entity.BrowseRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrowseRecordMapper {
    long countByExample(BrowseRecordExample example);

    int deleteByExample(BrowseRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrowseRecord record);

    int insertSelective(BrowseRecord record);

    List<BrowseRecord> selectByExample(BrowseRecordExample example);

    BrowseRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrowseRecord record, @Param("example") BrowseRecordExample example);

    int updateByExample(@Param("record") BrowseRecord record, @Param("example") BrowseRecordExample example);

    int updateByPrimaryKeySelective(BrowseRecord record);

    int updateByPrimaryKey(BrowseRecord record);

    List<Integer> getProductIds(Integer userId);
}