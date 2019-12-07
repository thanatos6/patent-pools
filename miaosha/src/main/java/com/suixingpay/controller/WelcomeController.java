package com.suixingpay.controller;

import com.suixingpay.pojo.CodeEnum;
import com.suixingpay.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kongjian
 */
@RestController
@RequestMapping("/welcome")
@Slf4j
public class WelcomeController {

    @RequestMapping("/test")
    @ResponseBody
    public Response test() {
        String hello = "hello kongjian";
        Map<String, Object> result = new HashMap<>();
        result.put("msg", hello);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);
        return response;
    }

    @RequestMapping("/exc")
    @ResponseBody
    public Response exc(@RequestParam("name") String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);
        return response;
    }
}
