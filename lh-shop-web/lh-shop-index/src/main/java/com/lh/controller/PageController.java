package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by laiHom on 2020/3/7.
 */
@Controller
public class PageController {
    //访问登陆页面
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //访问注册页面
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
}
