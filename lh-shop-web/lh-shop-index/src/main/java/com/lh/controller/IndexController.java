package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.lh.api.product.*;
import com.lh.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by laiHom on 2020/1/1.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

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

    @Reference
    private IBrowseRecordService browseRecordService;


    @RequestMapping("show/{pageIndex}/{pageSize}")
    public String showIndex(Model model,@PathVariable("pageIndex") Integer pageIndex,
                            @PathVariable("pageSize") Integer pageSize){
        List<CatalogOne> list = catalogOneService.getAll();

        List<Banner> bannerList = bannerService.getAll();

        List<HeadLines> headLinesList = headLinesService.getFive();

        PageInfo<Product> productList = productService.getAll(pageIndex,pageSize);
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
                                 @RequestParam(value = "specsId", required = false) Integer specsId,
                                 @CookieValue(name = "user_token",required = false) String userToken, Model model){

        //查看当前用户的登录状态
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());

        //当用户登录了，则将浏览信息存到数据库中以便推荐算法使用
        if(user != null){
           Boolean falg = browseRecordService.insertOrUpdate(user.getUserId(),productId.intValue());
        }
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



