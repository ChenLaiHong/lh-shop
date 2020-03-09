package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.IPersonService;

import com.lh.entity.Person;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/7.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private IPersonService personService;


    //检查用户名是否有重复
    @RequestMapping("checkName")
    @ResponseBody
    private Integer checkName(@RequestParam(value = "userName", required = false) String userName){
        int result = personService.checkName(userName);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    //检查手机号是否有重复
    @RequestMapping("checkPhone")
    @ResponseBody
    private Integer checkPhone(@RequestParam(value = "phone", required = false) String phone){
        int result = personService.checkPhone(phone);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    //用户注册
    @RequestMapping("register")
    private String register(Person person){
        personService.add(person);
        return "redirect:/toLogin";
    }

    //登陆操作
    @PostMapping("/login")
    public String login(Person person,Map<String,Object> map,ModelAndView mv) {

        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(person.getUserName(), MdUtil.md5(person.getUserPassword()));

        try {

            subject.login(token);
            //登陆成功
            mv.addObject("go_in", true);
            return "redirect:/index/show";
        }catch (UnknownAccountException e){
            //登陆用户名不存在
            map.put("msg", "请检查用户名、密码！");
        }catch (IncorrectCredentialsException e){
            //登陆失败，密码错误
            map.put("msg", "密码错误!");
        }
        return "redirect:/toLogin";

    }
}
