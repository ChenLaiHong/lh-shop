package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.IPersonService;
import com.lh.entity.HeadLines;
import com.lh.entity.PageBean;
import com.lh.entity.Payment;
import com.lh.entity.Person;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/30.
 */
@Controller
@RequestMapping("person")
public class PersonController {

    @Reference
    private IPersonService personService;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "limit", required = false) String rows,
                       @RequestParam(value = "q", required = false) String q,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("q", StringUtil.formatLike(q));

        List<Person> list = personService.pageList(map);
        Integer total = personService.getTotal(map);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        map.clear();
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        ResponseUtil.write(response, gson.toJson(map));
        return null;
    }

    @RequestMapping("/seeDetail")
    public ModelAndView toLook(@RequestParam(value = "userId", required = false) Integer userId) throws Exception {
        Person person = personService.findById(userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("person", person);
        mav.setViewName("/admin/personDetail");
        return mav;
    }
}
