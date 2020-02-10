package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.ICatalogOneService;
import com.lh.entity.CatalogOne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by laiHom on 2020/1/1.
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @Reference
    private ICatalogOneService catalogOneService;

    @RequestMapping("show")
    public String showIndex(Model model){
        List<CatalogOne> list = catalogOneService.getAll();

        //列表
        model.addAttribute("list",list);
        return "index";
    }
}
