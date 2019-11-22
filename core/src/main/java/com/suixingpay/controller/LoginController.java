package com.suixingpay.controller;


import com.suixingpay.pojo.User;
import com.suixingpay.service.Impl.UserImpl;
import com.suixingpay.service.UserService;
import com.suixingpay.util.ZhuanliUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class LoginController {
        private static final Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @PostMapping("/login")//一般注册都是写入到后台所以是post
    public  String  login(@RequestParam(value = "password") String password,
                          @RequestParam(value = "account") String account,
                          HttpSession session){

        try {
            //System.out.println(userService);

            User user =userService.login(password,account);
            System.out.println(user);

            if (user==null){
                return  String.valueOf(505);
            }
            /*if (StringUtils.isEmpty(password)){
                return  ZhuanliUtil.getJSONString("密码不能为空");
            }
            if (StringUtils.isEmpty(account)){
               return ZhuanliUtil.getJSONString("账号不能为空");
            }*/
            Date now=new Date();
            user.setCreateDate(now);
            session.setAttribute("user",user);
            return  String.valueOf(200);


        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            logger.error("登陆异常");
            return  ZhuanliUtil.getJSONString(500,"登录异常");
        }
    }


}
