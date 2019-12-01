package com.lh.mapper;

import com.lh.entity.UsersMemberReceiveAddress;

public interface UsersMemberReceiveAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UsersMemberReceiveAddress record);

    int insertSelective(UsersMemberReceiveAddress record);

    UsersMemberReceiveAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UsersMemberReceiveAddress record);

    int updateByPrimaryKey(UsersMemberReceiveAddress record);
}