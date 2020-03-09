package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.*;
import com.lh.entity.*;
import com.lh.utils.ItdragonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/1/1.
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @Reference
    private ICatalogOneService catalogOneService;
    @Reference
    private IBannerService bannerService;

    @Reference
    private IHeadLinesService headLinesService;

    @Reference
    private IProductService productService;

    @Reference
    private IProductImageService productImageService;

    @Reference
    private IProductSpecsService productSpecsService;


    @RequestMapping("show")
    public String showIndex(Model model){
        List<CatalogOne> list = catalogOneService.getAll();

        List<Banner> bannerList = bannerService.getAll();

        List<HeadLines> headLinesList = headLinesService.getFive();

        List<Product> productList = productService.getAll();
        //分类列表
        model.addAttribute("list",list);
        //Banner列表
        model.addAttribute("bannerList",bannerList);
        model.addAttribute("newsList",headLinesList);
        model.addAttribute("productList",productList);
        return "index1";
    }

    //商品详情
    @RequestMapping("productDetails")
    public String productDetails(@RequestParam(value = "productId", required = false) Long productId,
                                 @RequestParam(value = "specsId", required = false) Integer specsId,Model model){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productId", productId);
        Product product = product = productService.getById(map);
        if(specsId != null){
            product.setProductSpecs(productSpecsService.findById(specsId));
        }
        List<ProductImage> productImageList = productImageService.getByPid(productId);
        List<ProductSpecs> productSpecsList = productSpecsService.getByPid(productId);
        model.addAttribute("product",product);
        model.addAttribute("productImageList",productImageList);
        model.addAttribute("productSpecsList",productSpecsList);
        return "introduction";

    }
}



