package com.lh.api.product;

import com.lh.entity.Payment;

import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/22.
 */
public interface IPaymentService {
    List<Payment> pageList(Map<String, Object> map);

    Integer getTotal(Map<String, Object> map);

    int add(Payment payment);

    Payment findById(Integer paymentId);

    int update(Payment payment);

    int delete(String ids);

    int updateState(Payment payment);

    List<Payment> getAll();
}
