package com.lh.mapper;

import com.lh.entity.MemberLevel;

public interface MemberLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    MemberLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);
}