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

    Boolean index = true;

    @PostMapping("/login")//一般注册都是写入到后台所以是post
    public  String  login(@RequestBody User user,
                          HttpSession session){

        try {
            //System.out.println(userService);
            String account=user.getAccount();
            String password=user.getPassword();

             user =userService.login(account,password);
            System.out.println(user);

        if (user == null) {
            return ZhuanliUtil.getJSONString(505, "");
        }
            /*if (StringUtils.isEmpty(password)){
                return  ZhuanliUtil.getJSONString("密码不能为空");
            }
            if (StringUtils.isEmpty(account)){
               return ZhuanliUtil.getJSONString("账号不能为空");
            }*/
        Date now = new Date();
        user.setCreateDate(now);
        session.setAttribute("user", user);
        return ZhuanliUtil.getJSONString(200, user);


    }catch (Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        logger.error("登陆异常");
        return  ZhuanliUtil.getJSONString(500,"登录异常");
    }
}


}
