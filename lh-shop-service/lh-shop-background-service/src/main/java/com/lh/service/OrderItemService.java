package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.pojo.CartItem;
import com.lh.api.product.IOrderItemService;
import com.lh.entity.OrderItems;
import com.lh.entity.OrderItemsExample;
import com.lh.entity.ProductSpecs;
import com.lh.mapper.OrderItemsMapper;
import com.lh.mapper.ProductSpecsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.lh.shop.common.util.StringUtil.StringIds;

/**
 * Created by laiHom on 2020/3/22.
 */
@Component
@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Override
    public int add(String prices, String ids, String nums, int result) {
        OrderItems orderItems = new OrderItems();
        List<Integer> idsList = StringIds(ids);
        List<Integer> numList = StringIds(nums);
        List<String> pricesList = StringIds(prices,1);

        for (int i=0;i<idsList.size();i++){
            ProductSpecs productSpecs = productSpecsMapper.selectByPrimaryKey(idsList.get(i));
            orderItems.setProductName(productSpecs.getSpecsName());
            orderItems.setProducrIcon(productSpecs.getProduct().getProductOneImage());
            orderItems.setProductNum(numList.get(i));
            orderItems.setProductPrice(new BigDecimal(pricesList.get(i)));
            orderItems.setOrderId(result);
            orderItems.setSpecsId(productSpecs.getSpecsId());
            orderItemsMapper.insertSelective(orderItems);
        }

        return 1;
    }

    @Override
    public List<OrderItems> findByOrderId(Integer id) {
        OrderItemsExample orderItemsExample = new OrderItemsExample();
        orderItemsExample.createCriteria().andOrderIdEqualTo(id);
        return orderItemsMapper.selectByExample(orderItemsExample);
    }

    @Override
    public int updateNum(Integer orderId) {
        OrderItemsExample orderItemsExample = new OrderItemsExample();
        orderItemsExample.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderItems> orderItemsList = orderItemsMapper.selectByExample(orderItemsExample);
        for (int i = 0; i < orderItemsList.size(); i++) {
           int specId = orderItemsList.get(i).getSpecsId();
           int num = orderItemsList.get(i).getProductNum();
           ProductSpecs productSpecs = productSpecsMapper.selectByPrimaryKey(specId);
           productSpecs.setSpecsStock(productSpecs.getSpecsStock()-num);
           productSpecsMapper.updateByPrimaryKeySelective(productSpecs);
        }
        return 1;
    }
}
