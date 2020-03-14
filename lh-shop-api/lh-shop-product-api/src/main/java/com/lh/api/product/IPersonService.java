package com.lh.api.product;

import com.lh.entity.Person;
import com.lh.shop.common.pojo.PageResultBean;

/**
 * Created by laiHom on 2020/3/4.
 */
public interface IPersonService {
    Person getUserName(String username);

    int checkName(String userName);

    int checkPhone(String phone);

    void add(Person person);

    Integer update(Person person);

    Person findById(Integer userId);
}
