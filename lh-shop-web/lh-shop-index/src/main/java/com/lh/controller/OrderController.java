package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.lh.api.product.*;
import com.lh.api.vo.OrderVO;
import com.lh.entity.*;
import com.lh.shop.common.pojo.ResultBean;
import com.lh.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    private ICommentService commentService;
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

    //一键支付跳转
    @RequestMapping("onePay")
    public String onePay(@RequestParam(value = "orderId", required = false) Integer orderId,
                         HttpServletRequest request, Model model){

        Person person = (Person) request.getSession().getAttribute("person");
        //获取到数据库相应信息返回页面显示
        OrderBasics orderBasics = orderService.findById(orderId);
        List<OrderItems> orderItemsList = orderItemService.findByOrderId(orderId);
        //获取当前用户默认收货地址
        Address address = addressService.findByUserId(person.getUserId());
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

    //订单支付
    @RequestMapping("pay")
    @ResponseBody
    public ResultData<OrderBasics> pay(@RequestParam(value = "street", required = false) String street,
                          @RequestParam(value = "province", required = false) String province,
                          HttpServletResponse response, Model model, OrderBasics orderBasics){
        orderBasics.setReceiverAddress(province+" "+street);
        int result = orderService.update(orderBasics);
        ResultData<OrderBasics> resultData = new ResultData<>();
        resultData.setData(orderBasics);
        resultData.setCode(String.valueOf(result));

        return resultData;
    }

    //订单支付成功页面
    @RequestMapping("paySuc")
    public ModelAndView pay(@RequestParam(value = "orderId", required = false) Integer orderId){

        ModelAndView mav = new ModelAndView();
        //修改相应商品的库存
        int result = orderItemService.updateNum(orderId);
        mav.addObject("orderBasics", orderService.findById(orderId));
        mav.setViewName("/success");
        return mav;
    }

    //订单详情
    @RequestMapping("detail")
    public String detail(@RequestParam(value = "orderId", required = false) Integer orderId, Model model){
        OrderBasics orderBasics = orderService.findByIdAndItems(orderId);
        model.addAttribute("orderBasics",orderBasics);
        return "orderInfo";
    }

    @RequestMapping("list")
    public String getList(HttpServletRequest request,
                          Model model){
        Person user = (Person) request.getSession().getAttribute("person");
//        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getUserId());
        //获取所有状态的订单
        List<OrderBasics> orderBasicsList = orderService.getAll(map);
        //获取未支付订单
        List<OrderBasics> orderBasicsListNoPay = orderService.getAllNoPay(map);
        if(!orderBasicsList.isEmpty()) {
            //获取已付款未发货订单
            List<OrderBasics> orderBasicsListNoSend = orderService.getAllNoSend(orderBasicsList);
            //获取已发货未收货订单
            List<OrderBasics> orderBasicsListNoReceive = orderService.getAllNoReceive(orderBasicsList);
            //获已收货未评价订单
            List<OrderBasics> orderBasicsListNoAssess = orderService.getAllNoAssess(orderBasicsList);

            model.addAttribute("orderBasicsListNoSend",orderBasicsListNoSend);
            model.addAttribute("orderBasicsListNoReceive",orderBasicsListNoReceive);
            model.addAttribute("orderBasicsListNoAssess",orderBasicsListNoAssess);
        }
        model.addAttribute("orderBasicsList",orderBasicsList);
        model.addAttribute("orderBasicsListNoPay",orderBasicsListNoPay);


        return "order";
    }

    //确认收货
    @RequestMapping("receOk")
    public String receOk(@RequestParam(value = "orderId", required = false) Integer orderId, HttpServletResponse response)
            throws Exception {
        Result finalResult = new Result();
        OrderBasics orderBasics = orderService.findById(orderId);
        int result = orderService.receOk(orderBasics);

        return "redirect:/orders/list";
    }

    //取消订单
    @RequestMapping("delOrder")
    public String delOrder(@RequestParam(value = "orderId", required = false) Integer orderId, HttpServletResponse response)
            throws Exception {
        Result finalResult = new Result();
        OrderBasics orderBasics = orderService.findById(orderId);
        int result = orderService.delOrder(orderBasics);

        return "redirect:/orders/list";
    }

    //评价商品
    @RequestMapping("comment")
    public String comment(@RequestParam(value = "orderId", required = false) Integer orderId, Model model)
            throws Exception {

        List<OrderItems> orderItemsList = orderItemService.findByOrderId(orderId);
        model.addAttribute("orderItemsList",orderItemsList);
        model.addAttribute("orderId",orderId);
        return "commentlist";
    }

    //提交商品评价
    @RequestMapping("addComment")
    public String addComment(Comment comment, @RequestParam(value = "orderId", required = false) Integer orderId,HttpServletRequest request)
            throws Exception {
        Person person = (Person) request.getSession().getAttribute("person");
        comment.setUserId(person.getUserId());
        int result = commentService.add(comment);
//        修改状态为已评价
        OrderBasics orderBasics = orderService.findById(orderId);
        orderService.comOver(orderBasics);
        return "redirect:/orders/list";
    }
    private void flushCookie(String uuid, HttpServletResponse response) {
        Cookie cookie = new Cookie("user_cart",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        response.addCookie(cookie);
    }
}
