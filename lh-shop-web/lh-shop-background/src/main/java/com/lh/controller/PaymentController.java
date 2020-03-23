package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.api.product.IPaymentService;
import com.lh.entity.Payment;
import com.lh.entity.PageBean;
import com.lh.entity.Result;
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
@RequestMapping("payment")
public class PaymentController {
    @Reference
    private IPaymentService paymentService;

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

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

        List<Payment> list = paymentService.pageList(map);
        Integer total = paymentService.getTotal(map);
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
        mav.addObject("paymentId", 0);
        mav.addObject("OImageUrl", "");
        mav.addObject("save_url", "/payment/add");
        mav.setViewName("/admin/paymentAddOrUpdate");
        return mav;
    }
    @RequestMapping("/add")
    public String add(Payment payment,
                      @RequestParam(value="OImageUrl") String OImageUrl,
                      HttpServletResponse response) throws Exception {

        int resultTotal = paymentService.add(payment);
        Gson gson = new Gson();
        ResponseUtil.write(response, gson.toJson(res(resultTotal)));
        return null;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "payId", required = false) Integer payId) throws Exception {
        Payment payment = paymentService.findById(payId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("payId", payId);
        mav.addObject("payment", payment);
        mav.addObject("OImageUrl", payment.getPayIcon());
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/payment/update?payId="+payId);
        mav.setViewName("/admin/paymentAddOrUpdate");
        return mav;
    }

    @RequestMapping("/update")
    public String update(Payment payment,
                         @RequestParam(value="OImageUrl") String OImageUrl,
                         HttpServletResponse response) throws Exception {
        if(OImageUrl != payment.getPayIcon() && OImageUrl.length()>0){
            String groupPath = OImageUrl.substring(image_server.length(), OImageUrl.length());
            fastFileStorageClient.deleteFile(groupPath);
        }
        int resultTotal = paymentService.update(payment);
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
        int result1 = paymentService.delete(ids);
        result.setSuccess(true);
        ResponseUtil.write(response, gson.toJson(result));
        return null;
    }

    //更新状态setState
    @RequestMapping("/setState")
    @ResponseBody
    public Result setState(@RequestParam(value = "payId", required = false) Integer payId, HttpServletResponse response)
            throws Exception {
        Result finalResult = new Result();
        Payment payment = paymentService.findById(payId);
        int result = paymentService.updateState(payment);
        return getResult(result);
    }

    private Result getResult(int result){
        Result finalResult = new Result();
        if (result > 0) {
            finalResult.setSuccess(true);
            finalResult.setMsg("操作成功");
        } else {
            finalResult.setSuccess(false);
            finalResult.setMsg("操作失败");
        }
        return finalResult;

    }
}
