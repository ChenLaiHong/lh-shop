package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.ICatalogOneService;
import com.lh.api.product.ICatalogThreeService;
import com.lh.api.product.ICatalogTwoService;
import com.lh.entity.CatalogThree;
import com.lh.entity.PageBean;
import com.lh.entity.Result;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("catalogThree")
public class CatalogThreeController {

    @Reference
    private ICatalogThreeService catalogThreeService;

    @RequestMapping("/list")
    public String list(@RequestParam("twoId") Integer twoId,
                       @RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "limit", required = false) String rows,
                       @RequestParam(value = "q", required = false) String q,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("twoId", twoId);
        map.put("q", StringUtil.formatLike(q));

        List<CatalogThree> list = catalogThreeService.pageList(map);
        Integer total = catalogThreeService.getTotal(map);
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
    public ModelAndView toAdd(@RequestParam(value="twoId") Integer twoId) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("twoId", 0);
        mav.addObject("twoIdT", twoId);
        mav.addObject("save_url", "/catalogThree/add");
        mav.setViewName("/admin/catalogThreeAddOrUpdate");
        return mav;
    }
    @RequestMapping("/add")
    public String add(CatalogThree CatalogThree,@RequestParam(value="twoIdT") Integer twoId, HttpServletResponse response) throws Exception {

        CatalogThree.setTwoId(twoId);
        int resultTotal = catalogThreeService.add(CatalogThree);
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(res(resultTotal)));
        return null;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "threeId", required = false) Integer threeId) throws Exception {
        CatalogThree catalogThree = catalogThreeService.findById(threeId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("threeId", threeId);
        mav.addObject("catalogThree", catalogThree);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/catalogThree/update?threeId="+threeId);
        mav.setViewName("/admin/catalogThreeAddOrUpdate");
        return mav;
    }

    @RequestMapping("/update")
    public String update(CatalogThree CatalogThree, HttpServletResponse response) throws Exception {

        int resultTotal = catalogThreeService.update(CatalogThree);
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
        catalogThreeService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    /**
     * 前往三级管理页
     * @return
     */
    @RequestMapping(value = "/page")
    public String twoPage(@RequestParam("twoId") Integer twoId, Model model) {
        model.addAttribute("twoId", twoId);
        return "/admin/catalogThreeManage";
    }


}
