package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by laiHom on 2020/2/4.
 */
@Controller
public class PageController {

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
//        return "/admin/test";
    }
}
