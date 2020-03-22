package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.ICompanyService;
import com.lh.api.product.IProductImageService;
import com.lh.api.product.IProductService;
import com.lh.entity.ExpressCompany;
import com.lh.entity.PageBean;
import com.lh.entity.ProductImage;
import com.lh.entity.Result;
import com.lh.shop.common.pojo.ResultBean;
import com.lh.shop.common.util.ResponseUtil;
import com.lh.shop.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
 * Created by laiHom on 2020/3/22.
 */
@Controller
@RequestMapping("company")
public class CompanyController {
    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Reference
    private ICompanyService companyService;


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

        List<ExpressCompany> list = companyService.pageList(map);
        Integer total = companyService.getTotal(map);
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
        mav.addObject("companyId", 0);
        mav.addObject("OImageUrl", "");
        mav.addObject("save_url", "/company/add");
        mav.setViewName("/admin/companyAddOrUpdate");
        return mav;
    }
    @RequestMapping("/add")
    public String add(ExpressCompany company,
                      @RequestParam(value="OImageUrl") String OImageUrl,
                      HttpServletResponse response) throws Exception {

        int resultTotal = companyService.add(company);
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(res(resultTotal)));
        return null;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "companyId", required = false) Integer companyId) throws Exception {
        ExpressCompany company = companyService.findById(companyId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("companyId", companyId);
        mav.addObject("company", company);
        mav.addObject("OImageUrl", company.getCompanyIcon());
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/company/update?companyId="+companyId);
        mav.setViewName("/admin/companyAddOrUpdate");
        return mav;
    }

    @RequestMapping("/update")
    public String update(ExpressCompany company,
                         @RequestParam(value="OImageUrl") String OImageUrl,
                         HttpServletResponse response) throws Exception {

        if(OImageUrl != company.getCompanyIcon() && OImageUrl.length()>0){
            String groupPath = OImageUrl.substring(image_server.length(), OImageUrl.length());
            fastFileStorageClient.deleteFile(groupPath);
        }

        int resultTotal = companyService.update(company);
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
        companyService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    //更新状态setState
    @RequestMapping("/setState")
    @ResponseBody
    public Result setState(@RequestParam(value = "companyId", required = false) Integer companyId, HttpServletResponse response)
            throws Exception {
        Result finalResult = new Result();
        ExpressCompany company = companyService.findById(companyId);
        int result = companyService.updateState(company);
        if(result > 0){
            finalResult.setSuccess(true);
            finalResult.setMsg("修改成功");
        }else {
            finalResult.setSuccess(false);
            finalResult.setMsg("修改失败");
        }
        return finalResult;
    }
}
