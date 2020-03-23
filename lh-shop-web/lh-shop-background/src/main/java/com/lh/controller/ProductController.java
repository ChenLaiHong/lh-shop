package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.*;
import com.lh.api.vo.ProductVO;
import com.lh.entity.*;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
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

import static com.lh.shop.common.util.StringUtil.findPath;

/**
 * Created by laiHom on 2020/3/1.
 */
@Controller
@RequestMapping("product")
public class ProductController {
    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Reference
    private ISearchService searchService;
    @Reference
    private IProductService productService;

    @Reference
    private ICatalogOneService catalogOneService;

    @Reference
    private ICatalogTwoService catalogTwoService;


    @Reference
    private ICatalogThreeService catalogThreeService;



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

        List<Product> list = productService.pageList(map);
        Integer total = productService.getTotal(map);
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

        List<CatalogOne> catalogOneList = catalogOneService.getOne();
        List<CatalogTwo> catalogTwoList = catalogTwoService.getTwo();

        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("productId", 0);
        mav.addObject("images", "");
        mav.addObject("catalogOneList", catalogOneList);
        mav.setViewName("/admin/productAddOrUpdate");
        return mav;
    }


    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "productId", required = false) Integer productId) throws Exception {
        Product product = productService.findById(productId);
        List<CatalogOne> catalogOneList = catalogOneService.getOne();
        List<CatalogTwo> catalogTwoList = catalogTwoService.getTwo();
        List<CatalogThree> catalogThreeList = catalogThreeService.getThree();

        ModelAndView mav = new ModelAndView();
        mav.addObject("productId", productId);
        mav.addObject("product", product);
        mav.addObject("catalogOneList", catalogOneList);
        mav.addObject("catalogTwoList", catalogTwoList);
        mav.addObject("catalogThreeList", catalogThreeList);
        mav.addObject("images", product.getImages());
        mav.addObject("btn_text", "修改");
        mav.setViewName("/admin/productAddOrUpdate");
        return mav;
    }

    @RequestMapping("/addOrUpdate")
    public ModelAndView add(Product product, HttpServletResponse response, @RequestParam("oneImage") MultipartFile file,
                            @RequestParam("newImg") String newImg, @RequestParam("newImages") String newImages) throws Exception {
        ModelAndView mav = new ModelAndView();
        Integer productId = product.getProductId();

        ProductVO productVO = new ProductVO();
        int resultTotal = 0;
        if (productId == 0) {
            //如果上传了图片
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                String path = getPath(file);
                product.setProductOneImage(path);
            }
            product.setImages(newImages);

            productVO.setProduct(product);
            resultTotal = productService.add(productVO);
            //resultTotal是添加返回的主键
            searchService.updateById(resultTotal);
        } else {
            String groupPath = null;
            String oldImage = product.getProductOneImage();

            //如果旧的照片存在并且重新上传了图片则删除旧的把图片地址替换成新的
            if(StringUtils.isNotBlank(file.getOriginalFilename())){
                if(oldImage.length()>0 ) {
                    groupPath = oldImage.substring(image_server.length(), oldImage.length());
                    fastFileStorageClient.deleteFile(groupPath);
                }
                String path = getPath(file);
                product.setProductOneImage(path);
            }




            //当旧的有值
            if(product.getImages().length() != 0 ){
                List resultO = findPath(product.getImages().split(","));
                //当新的有值时
                if(product.getNewImages().length() != 0){
                    List resultN = findPath(product.getNewImages().split(","));
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
            resultTotal = productService.update(product);
            searchService.updateById(productId);
        }

        mav.setViewName("/admin/productManage");
        return mav;
    }

    //添加sku
    @RequestMapping("/ToAddTwo")
    public ModelAndView ToAddTwo(@RequestParam(value = "oneId", required = false) Integer oneId) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加二级");
        mav.addObject("oneId", oneId);
        mav.addObject("save_url", "/catalogTwo/add");
        mav.setViewName("/admin/catalogTwoAddOrUpdate");
        return mav;
    }
    //删除
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
            throws Exception {
        Gson gson = new Gson();
        Result result = new Result();
        productService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    public String getPath(MultipartFile file) {
        //1.获取到文件对象，将文件对象上传FastDFS上
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String path = null;
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
            //2.把服务器的文件保存地址返回给客户端
            String fullPath = storePath.getFullPath();
            path = new StringBuilder(image_server).append(fullPath).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @RequestMapping("/seeDetail")
    public ModelAndView toLook(@RequestParam(value = "productId", required = false) Integer productId) throws Exception {
        Product product = productService.findById(productId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("product", product);
        mav.setViewName("/admin/productDetail");
        return mav;
    }

}
