package com.suixingpay.service;

import java.util.HashMap;
import java.util.Map;

public class UserLoginDemoService {
    public Map getUserInfo(String token) {
        Map<String, Object> result = new HashMap<>();
        if (token.equals("admin")) {
            result.put("name", "管理员");
            result.put("role", "admin");
            result.put("id", 2);
            result.put("token", "admin");
        } else {
            result.put("name", "编辑者");
            result.put("role", "editor");
            result.put("id", 3);
            result.put("token", "editor");
        }
        return result;
    }
}
