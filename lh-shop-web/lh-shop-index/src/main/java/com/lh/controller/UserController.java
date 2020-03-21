package com.lh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lh.api.product.IAddressService;
import com.lh.api.product.IPersonService;

import com.lh.entity.Address;
import com.lh.entity.Person;
import com.lh.entity.Result;
import com.lh.shop.common.util.HttpClientUtils;
import com.lh.shop.common.util.MdUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by laiHom on 2020/3/7.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private IPersonService personService;

    @Reference
    private IAddressService addressService;

    //检查用户名是否有重复
    @RequestMapping("checkName")
    @ResponseBody
    private Integer checkName(@RequestParam(value = "userName", required = false) String userName){
        int result = personService.checkName(userName);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    //检查手机号是否有重复
    @RequestMapping("checkPhone")
    @ResponseBody
    private Integer checkPhone(@RequestParam(value = "phone", required = false) String phone){
        int result = personService.checkPhone(phone);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    //用户注册
    @RequestMapping("register")
    private String register(Person person){
        personService.add(person);
        return "redirect:/toLogin";
    }

    //登陆操作
    @PostMapping("/login")
    public String login(Person person, Map<String,Object> map, ModelAndView mv
            , HttpServletResponse response, HttpServletRequest request,
                        @CookieValue(name = "user_cart",required = false) String userCartToken) {

        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(person.getUserName(), MdUtil.md5(person.getUserPassword()));

        try {
            subject.login(token);

            Person currentUser = personService.getUserByNameAndPass(person.getUserName(),person.getUserPassword());
            request.getSession().setAttribute("personName",currentUser.getUserName());
            request.getSession().setAttribute("person", currentUser);
//        session2.setAttribute("person", person);
            //3.1 生成唯一的标识
            String uuid = UUID.randomUUID().toString();
            //3.2 构造一个cookie
            Cookie cookie = new Cookie("user_token",uuid);
            cookie.setPath("/");
            //基于安全控制，只允许通过后端来获取到cookie的信息
            //不能在前端通过脚本获取到cookie document.cookies
            cookie.setHttpOnly(true);
            //3.3 redis中保存凭证信息
            StringBuilder redisKey = new StringBuilder("user:token:").append(uuid);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set(redisKey.toString(),currentUser);
            //设置有效期
            redisTemplate.expire(redisKey.toString(),30, TimeUnit.MINUTES);
            response.addCookie(cookie);
            //登陆成功
            //登录成功之后，应该是调用购物车合并的接口
            //需要有一个工具，来模拟浏览器发送http请求
            //HttpClient---> Apache
            StringBuilder value = new StringBuilder("user_token=");
            value.append(uuid);
            value.append(";");
            value.append("user_cart=");
            value.append(userCartToken);

            Map<String,String> params = new HashMap<>();
            params.put("Cookie",value.toString());

            HttpClientUtils.doGetWithHeaders("http://localhost:9091/cart/merge",params);

            return "redirect:/index/show/1/8";
        }catch (UnknownAccountException e){
            //登陆用户名不存在
            map.put("msg", "请检查用户名、密码！");
        }catch (IncorrectCredentialsException e){
            //登陆失败，密码错误
            map.put("msg", "密码错误!");
        }
        return "redirect:/toLogin";

    }

    //去到个人中心页面
    @RequestMapping("/information")
    public String Information(Model model,HttpServletRequest request){
        Person person = (Person) request.getSession().getAttribute("person");
        Person person1 = personService.findById(person.getUserId());
        model.addAttribute("person",person1);
        return "information";
    }

    //保存个人信息saveInfo
    @PostMapping("/saveInfo")
    public String saveInfo(Person person, @RequestParam("oneIoc") MultipartFile file, @RequestParam("newImg") String newImg){
        String oldImage = person.getImageUrl();

        String newImage = newImg;
        if (oldImage.length() != 0) {
            if (newImage.length() != 0) {
                String groupPath = oldImage.substring(image_server.length(), oldImage.length());
                fastFileStorageClient.deleteFile(groupPath);
                //如果上传了图片
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    String path = getPath(file);
                    person.setImageUrl(path);
                }
            }
        } else {
            if (newImage != null) {
                //如果上传了图片
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    String path = getPath(file);
                    person.setImageUrl(path);
                }
            }
        }

        Integer result = personService.update(person);
        return "redirect:/user/information";
    }

    //去到个人地址页面
    @RequestMapping("/address")
    public String Address(Model model,HttpServletRequest request){
        Person person = (Person) request.getSession().getAttribute("person");
        List<Address> addressList = addressService.list(person.getUserId());
        model.addAttribute("addressList",addressList);
        model.addAttribute("userId",person.getUserId());
        return "address";
    }

    //去修改地址页面
    @RequestMapping("/updateAddress")
    public String updateAddress(Model model,@RequestParam(value = "addressId", required = false) Integer  addressId) {
        Address address = addressService.findById(addressId);
        model.addAttribute("address",address);
        return "updateAddress";
    }
        //添加或修改收货地址
    @PostMapping("/addOrUpdateAddress")
    public String addAddress(Address address){
        if(address.getAddressId() == null){
            addressService.add(address);
        }else {
            addressService.update(address);
        }

        return "redirect:/user/address";
    }

    //设置默认地址
    @PostMapping("/addDefaultAddress")
    @ResponseBody
    public Result addDefaultAddress(Integer addressId){
        Result finalResult = new Result();
        Integer result = addressService.updateById(addressId);
        if(result > 0){
            finalResult.setSuccess(true);
            finalResult.setMsg("设置成功");
        }else {
            finalResult.setSuccess(false);
            finalResult.setMsg("设置失败");
        }
        return finalResult;
    }

    //删除地址
    @GetMapping("/delAddress")
    public String delAddress(@RequestParam(value = "addressId", required = false) Integer  addressId){
        addressService.delete(addressId);
        return "redirect:/user/address";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletResponse response,
                         @CookieValue(name = "user_token",required = false) String uuid) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //1.删除cookie
        Cookie cookie = new Cookie("user_token",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //让cookie失效
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //2.删除redis的凭证
        StringBuilder redisKey = new StringBuilder("user:token:").append(uuid);
        //
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.delete(redisKey.toString());
        return "index";
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
}
