package com.lh.api.product;

import com.lh.entity.Person;
import com.lh.shop.common.pojo.PageResultBean;
import com.lh.shop.common.pojo.ResultBean;

import java.util.List;
import java.util.Map;

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

    Person getUserByNameAndPass(String userName, String userPassword);

    ResultBean checkIsLogin(String uuid);

    List<Person> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);
}
