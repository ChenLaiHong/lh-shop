package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.ICatalogOneService;
import com.lh.api.product.ICatalogTwoService;
import com.lh.entity.*;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.ResponseUtil.res;

/**
 * Created by laiHom on 2020/2/4.
 */
@Controller
@RequestMapping("catalogTwo")
public class CatalogTwoController {

    @Reference
    private ICatalogOneService catalogOneService;

    @Reference
    private ICatalogTwoService catalogTwoService;


    @RequestMapping("/list")
    public String list(@RequestParam("oneId") Integer oneId,
                       @RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "limit", required = false) String rows,
                       @RequestParam(value = "q", required = false) String q,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("oneId", oneId);
        map.put("q", StringUtil.formatLike(q));

        List<CatalogTwo> list = catalogTwoService.pageList(map);
        Integer total = catalogTwoService.getTotal(map);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        map.clear();
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        ResponseUtil.write(response, gson.toJson(map));
        return null;
    }


    @RequestMapping("/toAdd")
    public ModelAndView toAdd(@RequestParam(value="oneId") Integer oneId) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("twoId", 0);
        mav.addObject("oneIdT", oneId);
        mav.addObject("save_url", "/catalogTwo/add");
        mav.setViewName("/admin/catalogTwoAddOrUpdate");
        return mav;
    }
    @RequestMapping("/add")
    public String add(CatalogTwo catalogTwo, @RequestParam(value="oneIdT") Integer oneId, HttpServletResponse response) throws Exception {

        catalogTwo.setOneId(oneId);
        int resultTotal = catalogTwoService.add(catalogTwo);
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(res(resultTotal)));
        return null;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "twoId", required = false) Integer twoId) throws Exception {
        CatalogTwo catalogTwo = catalogTwoService.findById(twoId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("twoId", twoId);
        mav.addObject("catalogTwo", catalogTwo);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/catalogTwo/update?twoId="+twoId);
        mav.setViewName("/admin/catalogTwoAddOrUpdate");
        return mav;
    }

    @RequestMapping("/update")
    public String update(CatalogTwo catalogTwo, HttpServletResponse response) throws Exception {

        int resultTotal = catalogTwoService.update(catalogTwo);
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(res(resultTotal)));
        return null;
    }

    //删除
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
            throws Exception {
        Gson gson = new Gson();
        Result result = new Result();
        catalogTwoService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    /**
     * 前往二级管理页
     * @return
     */
    @RequestMapping(value = "/page")
    public String twoPage(@RequestParam("oneId") Integer oneId, Model model) {
        CatalogOne catalogOne = catalogOneService.findById(oneId);
        model.addAttribute("oneId", oneId);
        model.addAttribute("catalogOne", catalogOne);
        return "/admin/catalogTwoManage";
    }

    @ResponseBody
    @PostMapping("/getTwo")
    public List<CatalogTwo> getTwo(Integer oneId) {
        List<CatalogTwo> catalogTwoList = catalogTwoService.selectTwoByOneId(oneId);
        return catalogTwoList;
    }
}
