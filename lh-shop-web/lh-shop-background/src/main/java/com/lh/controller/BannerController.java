package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.IBannerService;
import com.lh.api.product.ICatalogOneService;
import com.lh.entity.*;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import com.lh.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/2/4.
 */
@Controller
@RequestMapping("banner")
public class BannerController {

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private FileUpload fileUpload;

    @Reference
    private IBannerService bannerService;

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

        List<Banner> list = bannerService.pageList(map);
        Integer total = bannerService.getTotal(map);
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
        mav.addObject("bannerId", 0);
        mav.setViewName("/admin/bannerAddOrUpdate");
        return mav;
    }


    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "bannerId", required = false) Integer bannerId) throws Exception {
        Banner banner = bannerService.findById(bannerId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("bannerId", bannerId);
        mav.addObject("banner", banner);
        mav.addObject("btn_text", "修改");
        mav.setViewName("/admin/bannerAddOrUpdate");
        return mav;
    }

    @RequestMapping("/addOrUpdate")
    public ModelAndView add(Banner banner, HttpServletResponse response, @RequestParam("oneIoc") MultipartFile file, @RequestParam("newImg") String newImg) throws Exception {
        ModelAndView mav = new ModelAndView();
        //添加
        Integer bannerId = banner.getBannerId();
        int resultTotal = 0;
        if (bannerId == 0) {
            //如果上传了图片
            if (file != null) {
                String path = fileUpload.getPath(file);
                banner.setBannerImage(path);
            }
            resultTotal = bannerService.add(banner);
        } else {
            String oldImage = banner.getBannerImage();

            String newImage = newImg;
            if (oldImage.length() != 0) {
                if (newImage.length() != 0) {
                    String groupPath = oldImage.substring(image_server.length(), oldImage.length());
                    fastFileStorageClient.deleteFile(groupPath);

                    System.out.println(oldImage.length());
                    //如果上传了图片
                    if (file != null) {
                        String path = fileUpload.getPath(file);
                        banner.setBannerImage(path);
                    }
                }
            } else {
                if (newImage != null) {
                    //如果上传了图片
                    if (file != null) {
                        String path = fileUpload.getPath(file);
                        banner.setBannerImage(path);
                    }
                }
            }
            resultTotal = bannerService.update(banner);
        }
        mav.setViewName("/admin/bannerManage");
        return mav;
    }


    //删除
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
            throws Exception {
        Gson gson = new Gson();
        Result result = new Result();
        bannerService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    @RequestMapping("/toLook")
    public ModelAndView toLook(@RequestParam(value = "bannerId", required = false) Integer bannerId) throws Exception {
        Banner banner = bannerService.findById(bannerId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("banner", banner);
        mav.setViewName("/admin/bannerLook");
        return mav;
    }
//
//    public String getPath(MultipartFile file) {
//        //1.获取到文件对象，将文件对象上传FastDFS上
//        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
//        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        String path = null;
//        try {
//            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
//            //2.把服务器的文件保存地址返回给客户端
//            String fullPath = storePath.getFullPath();
//            path = new StringBuilder(image_server).append(fullPath).toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return path;
//    }


}
