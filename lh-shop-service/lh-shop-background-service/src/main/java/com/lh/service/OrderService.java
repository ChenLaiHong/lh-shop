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
import java.util.ArrayList;
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

    @Override
    public List<OrderBasics> getAllNoPay(Map<String, Object> map) {
        return orderBasicsMapper.getAllNoPay(map);
    }

    @Override
    public List<OrderBasics> getAllNoSend(List<OrderBasics> orderBasicsList) {
        List<OrderBasics> resultList = new ArrayList<>();
        for (int i = 0; i < orderBasicsList.size(); i++) {
            if(orderBasicsList.get(i).getState().equals("2")){
                resultList.add(orderBasicsList.get(i));
            }
        }
        return resultList;
    }

    @Override
    public List<OrderBasics> getAllNoReceive(List<OrderBasics> orderBasicsList) {
        List<OrderBasics> resultList = new ArrayList<>();
        for (int i = 0; i < orderBasicsList.size(); i++) {
            if(orderBasicsList.get(i).getState().equals("3")){
                resultList.add(orderBasicsList.get(i));
            }
        }
        return resultList;
    }

    @Override
    public List<OrderBasics> getAllNoAssess(List<OrderBasics> orderBasicsList) {
        List<OrderBasics> resultList = new ArrayList<>();
        for (int i = 0; i < orderBasicsList.size(); i++) {
            if(orderBasicsList.get(i).getState().equals("4")){
                resultList.add(orderBasicsList.get(i));
            }
        }
        return resultList;
    }

    @Override
    public int update(OrderBasics orderBasics) {
        orderBasics.setState("2");
        return orderBasicsMapper.updateByPrimaryKeySelective(orderBasics);
    }

    @Override
    public OrderBasics findByIdAndItems(Integer orderId) {

        return orderBasicsMapper.findByIdAndItems(orderId);
    }

    @Override
    public List<OrderBasics> pageList(Map<String, Object> map) {
        return orderBasicsMapper.pageList(map);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        return orderBasicsMapper.getTotal(map);
    }

    @Override
    public int sendGoods(OrderBasics orderBasics) {
        orderBasics.setState("3");
        return orderBasicsMapper.updateByPrimaryKeySelective(orderBasics);
    }


}
