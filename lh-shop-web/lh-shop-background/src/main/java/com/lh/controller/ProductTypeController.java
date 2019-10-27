package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.IProductTypeService;
import com.lh.entity.ProductType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by laiHom on 2019/10/27.
 */
@RestController
@RequestMapping("productType")
public class ProductTypeController {
    @Reference
    private IProductTypeService productTypeService;

    @GetMapping("list")
    public List<ProductType> list(){
        return productTypeService.list();
    }
}
