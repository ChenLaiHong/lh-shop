package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IPersonService;
import com.lh.entity.Person;
import com.lh.entity.PersonExample;
import com.lh.mapper.PersonMapper;
import com.lh.shop.common.pojo.ResultBean;
import com.lh.shop.common.util.MdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by laiHom on 2020/3/4.
 */
@Component
@Service
public class PersonService implements IPersonService {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private RedisTemplate redisTemplate;
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

    @Override
    public Integer update(Person person) {
        person.setUpdateTime(new Date());
        return personMapper.updateByPrimaryKeySelective(person);
    }

    @Override
    public Person findById(Integer userId) {
        return personMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Person getUserByNameAndPass(String userName, String userPassword) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andUserNameEqualTo(userName).andUserPasswordEqualTo(MdUtil.md5(userPassword));

        return personMapper.selectByExample(personExample).get(0);
    }

    @Override
    public ResultBean checkIsLogin(String uuid) {
        //
        StringBuilder redisKey = new StringBuilder("user:token:").append(uuid);
        //
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person currentUser = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        if(currentUser != null){
            //刷新凭证的有效期
            //30分钟 20->30
            redisTemplate.expire(redisKey.toString(),30, TimeUnit.MINUTES);
            //return ResultBean.success(currentUser.getUsername());
            return new ResultBean(200,currentUser);
        }
        return ResultBean.error("用户未登录");
    }
}
