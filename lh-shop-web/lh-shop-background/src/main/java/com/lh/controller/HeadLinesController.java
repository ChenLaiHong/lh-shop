package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.IHeadLinesService;
import com.lh.entity.HeadLines;
import com.lh.entity.PageBean;
import com.lh.entity.Result;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lh.shop.common.util.StringUtil.findPath;

/**
 * Created by laiHom on 2020/2/4.
 */
@Controller
@RequestMapping("headLines")
public class HeadLinesController {

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Reference
    private IHeadLinesService headLinesService;

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

        List<HeadLines> list = headLinesService.pageList(map);
        Integer total = headLinesService.getTotal(map);
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
    public ModelAndView toAdd() throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("newsId", 0);
        mav.addObject("imageUrl", "");
        mav.addObject("save_url", "/headLines/addOrUpdate");
        mav.setViewName("/admin/headLinesAddOrUpdate");
        return mav;
    }


    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "newsId", required = false) Integer newsId) throws Exception {
        HeadLines headLines = headLinesService.findById(newsId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("newsId", newsId);
        mav.addObject("headLines", headLines);
        mav.addObject("imageUrl", headLines.getImageUrl());
        mav.addObject("save_url", "/headLines/addOrUpdate");
        mav.addObject("btn_text", "修改");
        mav.setViewName("/admin/headLinesAddOrUpdate");
        return mav;
    }

    @RequestMapping("/addOrUpdate")
    public ModelAndView add(HeadLines headLines, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        //添加
        Integer newsId = headLines.getNewsId();
        int resultTotal = 0;
        if (newsId == 0) {
            resultTotal = headLinesService.add(headLines);
        } else {

            String groupPath = null;
            //当旧的有值
            if(headLines.getImages().length() != 0 ){
                List resultO = findPath(headLines.getImages().split(","));
                //当新的有值时
                if(headLines.getImageUrl().length() != 0){
                    List resultN = findPath(headLines.getImageUrl().split(","));
                    for(int i=0;i<resultO.size();i++){
                        if(resultN.contains(resultO.get(i)) == false){
                            groupPath = String.valueOf(resultO.get(i)).substring(image_server.length(), String.valueOf(resultO.get(i)).length());
                            fastFileStorageClient.deleteFile(groupPath);
                        }
                    }
                    //当新的没有值，那就把旧的里面的值删除
                }else {
                    for(int i =0;i<resultO.size();i++){
                        groupPath = String.valueOf(resultO.get(i)).substring(image_server.length(), String.valueOf(resultO.get(i)).length());
                        System.out.println(groupPath+"输出地址。。。。。。。。");
                        fastFileStorageClient.deleteFile(groupPath);
                    }
                }
            }

            resultTotal = headLinesService.update(headLines);
        }
        mav.setViewName("/admin/headLinesManage");
        return mav;
    }


    //删除
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
            throws Exception {
        Gson gson = new Gson();
        Result result = new Result();
        headLinesService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    @RequestMapping("/seeDetail")
    public ModelAndView toLook(@RequestParam(value = "newsId", required = false) Integer newsId) throws Exception {
        HeadLines headLines = headLinesService.findById(newsId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("headLines", headLines);
        mav.setViewName("/admin/headLinesDetail");
        return mav;
    }



}
