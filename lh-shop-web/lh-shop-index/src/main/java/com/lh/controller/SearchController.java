package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lh.api.product.ISearchService;
import com.lh.entity.Product;
import com.lh.shop.common.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private ISearchService searchService;

    @RequestMapping("initAllData")
    @ResponseBody
    public ResultBean initAllData(){
        return searchService.initAllData();
    }

    @RequestMapping("searchByKeyWord")
    public String searchByKeyWord(String keyWord, Model model){
        //
        List<Product> list = searchService.searchByKeyWord(keyWord);

        model.addAttribute("list",list);
        return "search";
    }
}
