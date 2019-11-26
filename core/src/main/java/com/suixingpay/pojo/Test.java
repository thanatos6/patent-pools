package com.suixingpay.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/25 22:24
 */
public class Test {
    public static void main(String[] args) {
        Response<User> response = Response.getInstance(CodeEnum.SUCCESS);
        Response<Map<String, List<User>>> instance = Response.getInstance(CodeEnum.SUCCESS, new HashMap<String, List<User>>());
    }
}
