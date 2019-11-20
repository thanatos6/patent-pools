package com.suixingpay.common.controller;


import com.suixingpay.common.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {
        private static final Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Resource
    UserService userService;

    @PostMapping(path = {"/login"})//一般注册都是写入到后台所以是post
    public  String  login(Model model, @RequestParam(value = "password") String password, @RequestParam(value = "name")
                        String name, @RequestParam(value = "account") String account,
                        @RequestParam(value = "remember",defaultValue = "false") boolean b,
                        HttpServletResponse httpResponse){
        try {
            Map<String,String> map=userService.login(name,password,account);
            if (map.containsKey("ticket")){
                Cookie cookie=new Cookie("ticket",map.get("ticket"));

                cookie.setPath("/");
                httpResponse.addCookie(cookie);
                if (b){
                    cookie.setMaxAge(3600*24*5);
                }
                httpResponse.addCookie(cookie);
                return "redirect:/";
            }else {
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }

        }catch (Exception e){
            logger.error("登陆异常");
            return  "login";
        }

    }

}
