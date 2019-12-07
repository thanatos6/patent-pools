package com.suixingpay.util;

import com.suixingpay.pojo.CodeEnum;
import com.suixingpay.pojo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kongjian
 * @Date: 2019/12/7
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionErrorHandler(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", e.getMessage());
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.FAIL, result);
        return response;
    }
}
