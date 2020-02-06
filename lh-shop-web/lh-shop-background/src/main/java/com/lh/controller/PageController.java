package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by laiHom on 2020/2/4.
 */
@Controller
public class PageController {

    @RequestMapping("/toCatalogOne")
    public String toCrew(){
        return "/admin/catalogOneManage";
    }
}
