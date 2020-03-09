package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.lh.api.product.IBannerService;
import com.lh.api.product.ICatalogOneService;
import com.lh.api.product.IHeadLinesService;
import com.lh.api.product.ISearchService;
import com.lh.entity.Banner;
import com.lh.entity.CatalogOne;
import com.lh.entity.HeadLines;
import com.lh.entity.Product;
import com.lh.shop.common.pojo.PageResultBean;
import com.lh.shop.common.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiHom on 2020/3/8.
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ICatalogOneService catalogOneService;
    @Reference
    private IBannerService bannerService;

    @Reference
    private IHeadLinesService headLinesService;

    @Reference
    private ISearchService searchService;

    @RequestMapping("initAllData")
    @ResponseBody
    public ResultBean initAllData(){
        return searchService.initAllData();
    }

    @RequestMapping("searchByKeywords")
    public String searchByKeyWord(String keyWord, Model model,
                                  @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize){

        List<CatalogOne> list = catalogOneService.getAll();

        List<Banner> bannerList = bannerService.getAll();

        List<HeadLines> headLinesList = headLinesService.getFive();
        PageResultBean<Product> productList = searchService.searchByKeyWord(keyWord,pageIndex,pageSize);
//分类列表
        model.addAttribute("list",list);
        //Banner列表
        model.addAttribute("bannerList",bannerList);
        model.addAttribute("newsList",headLinesList);
        model.addAttribute("productList",productList);
        return "index1";
    }
}
