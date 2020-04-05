package com.lh.controller;

import com.alibaba.dubbo.common.serialize.java.CompactedObjectInputStream;
import com.alibaba.dubbo.config.annotation.Reference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.IBrowseRecordService;
import com.lh.api.product.ICartService;
import com.lh.api.product.IPersonService;
import com.lh.entity.Person;
import com.lh.shop.common.pojo.ResultBean;
import com.lh.shop.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by laiHom on 2020/3/18.
 */
@Controller
@RequestMapping("cart")
public class CartController {

    //前后端分离的方式，约定接口
    //并行开发
    //开发接口
    //前端，伪造假数据
    //联调

    @Reference
    private ICartService cartService;

    @Reference
    private IPersonService personService;

    @Reference
    private IBrowseRecordService browseRecordService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("add")
    @ResponseBody
    public ResultBean add(@RequestParam(value = "productId", required = false) Integer productId,
                          @RequestParam(value = "count", required = false) Integer count,
                          @CookieValue(name = "user_cart",required = false) String uuid,
                          @CookieValue(name = "user_token",required = false) String userToken,
                          HttpServletResponse response,
                          HttpServletRequest request){

        //查看当前用户的登录状态
        //user:cart:userId
        //user:cart:uuid
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        String key = "";
        if(user != null){
            key = new StringBuilder("user:cart:").append(user.getUserId()).toString();
            //添加商品都购物车也要进行记录
            browseRecordService.insertOrUpdate(user.getUserId(),productId.intValue());
        }else{
            if(uuid == null){
                uuid = UUID.randomUUID().toString();
            }
            key = new StringBuilder("user:cart:").append(uuid).toString();
        }
        //写cookie到客户端
        flushCookie(uuid, response);
        return cartService.add(key.toString(),productId,count);
    }

    private void flushCookie(String uuid, HttpServletResponse response) {
        Cookie cookie = new Cookie("user_cart",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        response.addCookie(cookie);
    }

    //去购物车页面
    @RequestMapping("/toPage")
    public String toPage(){
        return "trolley";
    }
    //购物车列表
    @RequestMapping("list")
    public String list(@CookieValue(name = "user_cart",required = false) String uuid,
                       @CookieValue(name = "user_token",required = false) String userToken,
                           HttpServletResponse response,
                           HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        //定位--走到哪了
        //查看当前用户的登录状态
        //user:cart:userId
        //user:cart:uuid
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        String key = "";
        if(user != null){
            key = new StringBuilder("user:cart:").append(user.getUserId()).toString();
        }else{
            if(uuid == null){
                return null;
            }
            key = new StringBuilder("user:cart:").append(uuid).toString();
        }
        //写cookie到客户端
        flushCookie(uuid, response);
        ResultBean list = cartService.list(key.toString());
        map.put("data", list.getData());
        map.put("code", 0);
        ResponseUtil.write(response, gson.toJson(map));
        return null;
    }

    @RequestMapping("update/{productId}/{count}")
    @ResponseBody
    public ResultBean update(@PathVariable("productId") Integer productId,
                             @PathVariable("count") Integer count,
                             @CookieValue(name = "user_cart",required = false) String uuid,
                             @CookieValue(name = "user_token",required = false) String userToken,
                             HttpServletResponse response,
                             HttpServletRequest request){
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        //
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        String key = "";
        if(user != null){
            key = new StringBuilder("user:cart:").append(user.getUserId()).toString();
        }else{
            if(uuid == null){
                return ResultBean.error("购物车暂无商品信息！");
            }
            key = new StringBuilder("user:cart:").append(uuid).toString();
        }
        ResultBean resultBean = cartService.updateCount(key.toString(), productId, count);
        //刷cookie
        flushCookie(uuid,response);
        return resultBean;
    }

    @DeleteMapping("del")
    @ResponseBody
    public ResultBean del(@RequestParam(value = "productId", required = false) Integer productId,
                          @CookieValue(name = "user_cart",required = false) String uuid,
                          @CookieValue(name = "user_token",required = false) String userToken,
                          HttpServletResponse response,
                          HttpServletRequest request){
        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        //
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person user = (Person) redisTemplate.opsForValue().get(redisKey.toString());
        String key = "";
        if(user != null){
            key = new StringBuilder("user:cart:").append(user.getUserId()).toString();
        }else{
            if(uuid == null){
                return ResultBean.error("购物车暂无商品信息！");
            }
            key = new StringBuilder("user:cart:").append(uuid).toString();
        }
        ResultBean resultBean = cartService.del(key.toString(), productId);
        flushCookie(uuid,response);
        return resultBean;
    }

    @RequestMapping("merge")
    @ResponseBody
    public ResultBean merge(@CookieValue(name = "user_cart",required = false) String uuid,
                            @CookieValue(name = "user_token",required = false) String userToken,
                            HttpServletResponse response,
                            HttpServletRequest request){

        StringBuilder redisKey = new StringBuilder("user:token:").append(userToken);
        //
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Person currentUser = (Person) redisTemplate.opsForValue().get(redisKey.toString());

        if(currentUser == null){
            return ResultBean.error("未登录状态！");
        }
        if(uuid == null || "".equals(uuid)){
            return ResultBean.error("不存在未登录购物车,无需合并");
        }
        //
        String loginKey = new StringBuilder("user:cart:").append(currentUser.getUserId()).toString();
        String noLoginKey = new StringBuilder("user:cart:").append(uuid).toString();
        ResultBean resultBean = cartService.merge(noLoginKey, loginKey);
        //TODO 清除掉旧的cookie
        //重新写cookie，设置有效期为0，就是删除
        //1.删除cookie   627b8a37-7e49-4a14-9883-0b24f9fb0b80
//        System.out.println("uuid===="+uuid);
//        System.out.println("userToken===="+userToken);
        Cookie cookie = new Cookie("user_cart",uuid);
        cookie.setHttpOnly(true);
        //让cookie失效

        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return resultBean;
    }
}
