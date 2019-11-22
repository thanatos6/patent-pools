package com.suixingpay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-login")
public class UserLoginController {
    @RequestMapping("/demo")
    public Map<String, Object> login(@RequestParam("role") String role) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "登录成功");
        String adminStr = new String();
        adminStr = "admin";
        if (role.equals("admin")) {
            result.put("userName", "管理员");
            result.put("userRole", 1);
            result.put("userId", 2);
            result.put("token", "admin");
        } else {
            result.put("userName", "编辑者");
            result.put("userRole", 0);
            result.put("userId", 3);
            result.put("token", "editor");
        }
        return result;
    }
}
