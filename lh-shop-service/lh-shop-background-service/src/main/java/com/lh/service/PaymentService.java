package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IPaymentService;
import com.lh.entity.Payment;
import com.lh.entity.PaymentExample;
import com.lh.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/3/22.
 */
@Component
@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> pageList(Map<String, Object> map) {
        return paymentMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return paymentMapper.getTotal(map);
    }

    @Override
    public int add(Payment payment) {
        payment.setAddTime(new Date());
        payment.setUpdateTime(new Date());
        payment.setState("1");
        return paymentMapper.insertSelective(payment);
    }

    @Override
    public Payment findById(Integer paymentId) {
        return paymentMapper.selectByPrimaryKey(paymentId);
    }

    @Override
    public int update(Payment payment) {
        payment.setUpdateTime(new Date());
        return paymentMapper.updateByPrimaryKeySelective(payment);
    }

    @Override
    public int delete(String ids) {
        return paymentMapper.updateList(StringIds(ids));
    }

    @Override
    public int updateState(Payment payment) {
        if(payment.getState().equals("1")){
            payment.setState("0");
        }else {
            payment.setState("1");
        }
        return paymentMapper.updateByPrimaryKeySelective(payment);
    }

    @Override
    public List<Payment> getAll() {
        PaymentExample paymentExample = new PaymentExample();
        paymentExample.createCriteria().andStateEqualTo("1");
        return paymentMapper.selectByExample(paymentExample);
    }
}
