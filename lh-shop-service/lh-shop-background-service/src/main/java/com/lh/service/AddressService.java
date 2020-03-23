package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IAddressService;
import com.lh.entity.Address;
import com.lh.entity.AddressExample;
import com.lh.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by laiHom on 2020/3/14.
 */
@Component
@Service
public class AddressService implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> list(Integer userId) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId).andStateEqualTo(0);
        return addressMapper.selectByExample(addressExample);
    }

    @Override
    public void add(Address address) {
        address.setAddTime(new Date());
        address.setUpdateTime(new Date());
        address.setState(0);
        addressMapper.insertSelective(address);
    }

    @Override
    public Integer updateById(Integer addressId) {
        AddressExample example = new AddressExample();
        example.createCriteria().andDefaultAddressEqualTo(0);
        List<Address> addresses = addressMapper.selectByExample(example);
        //将之前的默认地址改成非默认
        if(addresses.size()>0) {
            Address address = addresses.get(0);
            address.setDefaultAddress(1);
            addressMapper.updateByPrimaryKeySelective(address);
        }
        //将新的默认地址设置上
        Address newAddress = addressMapper.selectByPrimaryKey(addressId);
        newAddress.setDefaultAddress(0);
     return addressMapper.updateByPrimaryKeySelective(newAddress);
    }

    @Override
    public void delete(Integer addressId) {
        //逻辑删除
        Address address = addressMapper.selectByPrimaryKey(addressId);
        address.setState(1);
        addressMapper.updateByPrimaryKeySelective(address);

    }

    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public Address findById(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public Address findByUserId(Integer userId) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId).andDefaultAddressEqualTo(0);
        return addressMapper.selectByExample(addressExample).get(0);
    }
}
