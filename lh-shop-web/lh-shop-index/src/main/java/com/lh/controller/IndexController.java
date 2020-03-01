package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.IBannerService;
import com.lh.api.product.ICatalogOneService;
import com.lh.api.product.IHeadLinesService;
import com.lh.entity.Banner;
import com.lh.entity.CatalogOne;
import com.lh.entity.HeadLines;
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
    @Reference
    private IBannerService bannerService;

    @Reference
    private IHeadLinesService headLinesService;

    @RequestMapping("show")
    public String showIndex(Model model){
        List<CatalogOne> list = catalogOneService.getAll();

        List<Banner> bannerList = bannerService.getAll();

        List<HeadLines> headLinesList = headLinesService.getFive();
        //分类列表
        model.addAttribute("list",list);
        //Banner列表
        model.addAttribute("bannerList",bannerList);
        model.addAttribute("newsList",headLinesList);
        return "index";
    }
}



