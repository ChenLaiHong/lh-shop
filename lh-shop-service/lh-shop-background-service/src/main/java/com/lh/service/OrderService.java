package com.lh.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.IOrderService;
import com.lh.api.vo.OrderVO;
import com.lh.entity.OrderBasics;
import com.lh.entity.OrderBasicsExample;
import com.lh.mapper.OrderBasicsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/22.
 */
@Component
@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderBasicsMapper orderBasicsMapper;


    @Override
    public int addFirst(OrderVO orderVO,double zumPrice, Integer userId) {
        OrderBasics orderBasics = orderVO.getOrderBasics();
        BigDecimal total = new BigDecimal(Double.toString(zumPrice));
        String orderFirst = String.valueOf(new Date().getTime());
        //时间戳+用户id组成订单号
        DecimalFormat g1=new DecimalFormat("0000000");
        String startZeroStr = g1.format(userId);
        String orderNumber = orderFirst+startZeroStr;
        orderBasics.setOrderNumber(orderNumber);
        orderBasics.setOrderTime(new Date());
        orderBasics.setTotalMoney(total);
        orderBasics.setUserId(userId);
        orderBasics.setState("1");
//        orderBasicsMapper.insertSelective(orderBasics);
        orderBasicsMapper.insertAndGetId(orderBasics);
        return orderBasics.getOrderId();
    }

    @Override
    public OrderBasics findByUserId(Integer userId) {

        return null;
    }

    @Override
    public OrderBasics findById(int result) {
        return orderBasicsMapper.selectByPrimaryKey(result);
    }

    @Override
    public List<OrderBasics> getAll(Map<String, Object> map) {
        return orderBasicsMapper.getAll(map);
    }
}
