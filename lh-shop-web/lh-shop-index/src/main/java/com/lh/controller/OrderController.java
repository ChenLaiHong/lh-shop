package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.*;
import com.lh.api.vo.OrderVO;
import com.lh.entity.*;
import com.lh.shop.common.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/22.
 */
@Controller
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Reference
    private ICartService cartService;
    @Reference
    private IOrderService orderService;
    @Reference
    private IOrderItemService orderItemService;
    @Reference
    private IPaymentService paymentService;
    @Reference
    private ICompanyService companyService;

    @Reference
    private IAddressService addressService;
    //生成订单
    @RequestMapping("subSettle")
    public String subSettle(@RequestParam(value = "ids", required = false) String ids,
                            @CookieValue(name = "user_cart",required = false) String uuid,
                            @RequestParam(value = "nums", required = false) String nums,
                            @RequestParam(value = "prices", required = false) String prices,
                            @RequestParam(value = "spaceName", required = false) String spaceName,
                            @CookieValue(name = "user_token",required = false) String userToken,
                            @RequestParam(value = "zumPrice", required = false) double zumPrice,
                            HttpServletResponse response,Model model,OrderBasics orderBasics1){
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        String key = new StringBuilder("user:cart:").append(user.getUserId()).toString();
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderBasics(orderBasics1);
        //1、先生成订单,得到订单返回的主键
        int result = orderService.addFirst(orderVO,zumPrice,user.getUserId());
        //2、生成订单项
        int resultItem = orderItemService.add(prices,ids,nums,result);
        //3、清理已经生成订单的cookie
        ResultBean resultBean = cartService.delIds(key.toString(), ids);
        flushCookie(uuid,response);

        //获取到数据库相应信息返回页面显示
        OrderBasics orderBasics = orderService.findById(result);
        List<OrderItems> orderItemsList = orderItemService.findByOrderId(result);
        //获取当前用户默认收货地址
        Address address = addressService.findByUserId(user.getUserId());
        //获取物流公司
        List<ExpressCompany> companyList = companyService.getAll();
        //获取支付方式
        List<Payment> paymentList = paymentService.getAll();
        model.addAttribute("orderBasics",orderBasics);
        model.addAttribute("orderItemsList",orderItemsList);
        model.addAttribute("address",address);
        model.addAttribute("companyList",companyList);
        model.addAttribute("paymentList",paymentList);
        return "pay";
    }

    @RequestMapping("list")
    public String getList(@CookieValue(name = "user_token",required = false) String userToken,
                          Model model){
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getUserId());
        //获取所有状态的订单
        List<OrderBasics> orderBasicsList = orderService.getAll(map);
        //获取未支付订单
        List<OrderBasics> orderBasicsListNoPay = orderService.getAllNoPay(map);
        model.addAttribute("orderBasicsList",orderBasicsList);
        model.addAttribute("orderBasicsListNoPay",orderBasicsListNoPay);


        return "order";
    }




    private void flushCookie(String uuid, HttpServletResponse response) {
        Cookie cookie = new Cookie("user_cart",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        response.addCookie(cookie);
    }
}
