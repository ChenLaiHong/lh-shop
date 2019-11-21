package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.IProductTypeService;
import com.lh.entity.ProductType;
import com.lh.shop.common.util.TreeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.lh.shop.common.util.TreeUtils.transProductType;

/**
 * Created by laiHom on 2019/10/27.
 */
//@RestController
@Controller
@RequestMapping("productType")
public class ProductTypeController {

    @Reference
    private IProductTypeService productTypeService;

    //    @GetMapping("list")
//    public List<ProductType> list(){
//        return productTypeService.list();
//    }
//
    @GetMapping("list")
    public String list(Model model) {
        List<ProductType> list =transProductType(productTypeService.list()) ;

//        List<ProductType> list =productTypeService.list() ;
        model.addAttribute("productTypeList",list);
        return "product_type/list";
    }
}
