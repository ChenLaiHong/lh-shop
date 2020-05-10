package com.lh.controller;

import com.lh.shop.common.util.MdUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/4.
 */
@Controller
public class PageController {

    //访问登陆页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //商品分类管理
    @RequestMapping("/toCatalogOne")
    public String toCrew(){
        return "/admin/catalogOneManage";
    }

    //Banner图管理
    @RequestMapping("/toBanner")
    public String toBanner(){
        return "/admin/bannerManage";
    }

    //新闻头条管理
    @RequestMapping("/toHeadLines")
    public String toHeadLines(){
        return "/admin/headLinesManage";
    }

    //商品管理
    @RequestMapping("/toProduct")
    public String toProduct(){
        return "/admin/productManage";
    }

    //订单管理
    @RequestMapping("/toOrders")
    public String toOrders(){
        return "/admin/orderManage";
    }

    //快递公司管理
    @RequestMapping("/toCompany")
    public String toCompany(){
        return "/admin/companyManage";
    }

    //支付方式管理
    @RequestMapping("/toPayment")
    public String toPayment(){
        return "/admin/paymentManage";
    }

    //用户管理
    @RequestMapping("/toPerson")
    public String toPerson(){
        return "/admin/personManage";
    }

    //登陆操作
    @PostMapping("/login")
    public String login(HttpServletRequest request, Map<String,Object> map, Model model) {
        String name = request.getParameter("adminName");
        String password = request.getParameter("adminPassword");
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(name, MdUtil.md5(password));

        try {
            subject.login(token);
            //登陆成功
            return "/admin/AdminMain";

        }catch (UnknownAccountException e){
            //登陆用户名不存在
            map.put("msg", "请检查用户名、密码！");

        }catch (IncorrectCredentialsException e){
            //登陆失败，密码错误
            map.put("msg", "密码错误!");
        }
        return "index";

    }
}
