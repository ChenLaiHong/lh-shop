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
}
