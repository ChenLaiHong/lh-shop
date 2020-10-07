package com.lh.api.product;

import com.lh.entity.OrderItems;

import java.util.List;

/**
 * Created by laiHom on 2020/3/22.
 */
public interface IOrderItemService {
    int add(String prices, String ids, String nums, int result);

    List<OrderItems> findByOrderId(Integer id);

    int updateNum(Integer orderId);
}
