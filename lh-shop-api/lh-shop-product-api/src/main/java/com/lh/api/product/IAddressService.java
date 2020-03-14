package com.lh.api.product;

import com.lh.entity.Address;

import java.util.List;

/**
 * Created by laiHom on 2020/3/14.
 */
public interface IAddressService {
    List<Address> list(Integer userId);

    void add(Address address);

    Integer updateById(Integer addressId);

    void delete(Integer addressId);

    void update(Address address);

    Address findById(Integer addressId);
}
