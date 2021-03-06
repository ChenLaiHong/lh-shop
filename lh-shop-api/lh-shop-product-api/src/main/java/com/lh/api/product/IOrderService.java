package com.lh.api.product;

import com.lh.api.vo.OrderVO;
import com.lh.entity.OrderBasics;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/22.
 */
public interface IOrderService {
    int addFirst(OrderVO orderVO,double zumPrice, Integer userId);

    OrderBasics findByUserId(Integer userId);

    OrderBasics findById(int result);

    List<OrderBasics> getAll(Map<String, Object> map);

    List<OrderBasics> getAllNoPay(Map<String, Object> map);

    List<OrderBasics> getAllNoSend(List<OrderBasics> orderBasicsList);

    List<OrderBasics> getAllNoReceive(List<OrderBasics> orderBasicsList);

    List<OrderBasics> getAllNoAssess(List<OrderBasics> orderBasicsList);

    int update(OrderBasics orderBasics);

    OrderBasics findByIdAndItems(Integer orderId);

    List<OrderBasics> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int sendGoods(OrderBasics orderBasics);

    int receOk(OrderBasics orderBasics);

    int delOrder(OrderBasics orderBasics);

    void comOver(OrderBasics orderBasics);
}
