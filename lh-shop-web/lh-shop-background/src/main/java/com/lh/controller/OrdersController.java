package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.IOrderItemService;
import com.lh.api.product.IOrderService;
import com.lh.entity.*;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/5/5.
 */
@Controller
@RequestMapping("adminOrders")
public class OrdersController {
    @Reference
    private IOrderService orderService;
    @Reference
    private IOrderItemService orderItemService;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "limit", required = false) String rows,
                       @RequestParam(value = "q", required = false) String q,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("q", StringUtil.formatLike(q));

        List<OrderBasics> list = orderService.pageList(map);
        Integer total = orderService.getTotal(map);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        map.clear();
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        ResponseUtil.write(response, gson.toJson(map));
        return null;
    }


    //更新状态setState
    @RequestMapping("/sendGoods")
    @ResponseBody
    public Result sendGoods(@RequestParam(value = "orderId", required = false) Integer orderId, HttpServletResponse response)
            throws Exception {
        Result finalResult = new Result();
        OrderBasics orderBasics = orderService.findById(orderId);

        int result = orderService.sendGoods(orderBasics);
        if (result > 0) {
            finalResult.setSuccess(true);
            finalResult.setMsg("操作成功");
        } else {
            finalResult.setSuccess(false);
            finalResult.setMsg("操作失败");
        }
        return finalResult;
    }
}
