package com.suixingpay.controller;


import com.github.pagehelper.util.StringUtil;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.Date;

@RestController
public class LoginController {
        private static final Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Action(name="login")
    @PostMapping("/login")//一般注册都是写入到后台所以是post
    public  String  login(@RequestBody(required =false)  User user
                          ){

        try {
            //System.out.println(userService);
            HttpSession session=request.getSession();
            String account=user.getAccount();
            String password=user.getPassword();
            user=userService.login(account,password);
            System.out.println(user);
            if (ZhuanliUtil.isBlank(password)){
                return  ZhuanliUtil.getJSONString("密码不能为空");
            }
            if (ZhuanliUtil.isBlank(account)){
               return ZhuanliUtil.getJSONString("账号不能为空");
            }

            if (user!=null){
                synchronized (this){
                userService.userByNumAndId(user.getNum()+1,user.getId());
                }

                session.setAttribute("user",user);
                System.out.println(user);
                return  ZhuanliUtil.getJSONString(200,user);

            }else {
                return  ZhuanliUtil.getJSONString(505,"账户或密码错误");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            logger.error("登陆异常");
            return  ZhuanliUtil.getJSONString(500,"登录异常");
        }
    }


}
