package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IPersonService;
import com.lh.entity.Person;
import com.lh.entity.PersonExample;
import com.lh.mapper.PersonMapper;
import com.lh.shop.common.util.MdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by laiHom on 2020/3/4.
 */
@Component
@Service
public class PersonService implements IPersonService {
    @Autowired
    private PersonMapper personMapper;
    @Override
    public Person getUserName(String username) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andUserNameEqualTo(username);
        return personMapper.selectByExample(personExample).get(0);
    }

    @Override
    public int checkName(String userName) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andUserNameEqualTo(userName);
        return personMapper.selectByExample(personExample).size();
    }

    @Override
    public int checkPhone(String phone) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andPhoneEqualTo(phone);
        return personMapper.selectByExample(personExample).size();
    }

    @Override
    public void add(Person person) {
        person.setUserPassword(MdUtil.md5(person.getUserPassword()));
        personMapper.insertSelective(person);
    }
}
