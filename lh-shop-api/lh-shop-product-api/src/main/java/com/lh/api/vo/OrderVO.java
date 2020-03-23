package com.lh.api.vo;

import com.lh.entity.OrderBasics;

import java.io.Serializable;

/**
 * Created by laiHom on 2020/3/22.
 */
public class OrderVO implements Serializable {

    private OrderBasics orderBasics;

    public OrderBasics getOrderBasics() {
        return orderBasics;
    }

    public void setOrderBasics(OrderBasics orderBasics) {
        this.orderBasics = orderBasics;
    }
}
