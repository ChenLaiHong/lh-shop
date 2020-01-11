package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.IProductTypeService;
import com.lh.entity.ProductType;
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
    private IProductTypeService productTypeService;

    @RequestMapping("show")
    public String showIndex(Model model){
        List<ProductType> list = productTypeService.list();
        model.addAttribute("list",list);
        return "index";
    }
}
