package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.ICatalogOneService;
import com.lh.api.product.ICatalogTwoService;
import com.lh.api.product.IProductImageService;
import com.lh.api.product.IProductService;
import com.lh.entity.*;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("productImage")
public class ImageController {

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Reference
    private IProductService productService;

    @Reference
    private IProductImageService productImageService;


    @RequestMapping("/list")
    public String list(@RequestParam("productId") Integer productId,
                       @RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "limit", required = false) String rows,
                       @RequestParam(value = "q", required = false) String q,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("productId", productId);
        map.put("q", StringUtil.formatLike(q));

        List<ProductImage> list = productImageService.pageList(map);
        Integer total = productImageService.getTotal(map);
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
    public ModelAndView toAdd(@RequestParam(value="productId") Integer productId) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("id", 0);
        mav.addObject("productId", productId);
        mav.addObject("OImageUrl", "");
        mav.addObject("save_url", "/productImage/add");
        mav.setViewName("/admin/productImageAddOrUpdate");
        return mav;
    }
    @RequestMapping("/add")
    public String add(ProductImage productImage,
                      @RequestParam(value="OImageUrl") String OImageUrl,
                      HttpServletResponse response) throws Exception {

        int resultTotal = productImageService.add(productImage);
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(res(resultTotal)));
        return null;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        ProductImage productImage = productImageService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("id", id);
        mav.addObject("productImage", productImage);
        mav.addObject("OImageUrl", productImage.getImageUrl());
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/productImage/update?id="+id);
        mav.setViewName("/admin/productImageAddOrUpdate");
        return mav;
    }

    @RequestMapping("/update")
    public String update(ProductImage productImage,
                         @RequestParam(value="OImageUrl") String OImageUrl,
                         HttpServletResponse response) throws Exception {

        if(OImageUrl != productImage.getImageUrl() && OImageUrl.length()>0){
            String groupPath = OImageUrl.substring(image_server.length(), OImageUrl.length());
            fastFileStorageClient.deleteFile(groupPath);
        }

        int resultTotal = productImageService.update(productImage);
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
        productImageService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    /**
     * 前往商品图片管理页
     * @return
     */
    @RequestMapping(value = "/page")
    public String twoPage(@RequestParam("productId") Integer productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("productId", productId);
        model.addAttribute("product", product);
        return "/admin/productImageManage";
    }


}
