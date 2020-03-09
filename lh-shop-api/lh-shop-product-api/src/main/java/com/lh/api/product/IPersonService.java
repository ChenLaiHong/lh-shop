package com.lh.api.product;

import com.lh.entity.Person;

/**
 * Created by laiHom on 2020/3/4.
 */
public interface IPersonService {
    Person getUserName(String username);

    int checkName(String userName);

    int checkPhone(String phone);

    void add(Person person);
}
