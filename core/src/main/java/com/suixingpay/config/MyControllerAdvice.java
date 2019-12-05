package com.suixingpay.config;

import com.suixingpay.util.ExceptionUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/04 23:32
 * 用于校验拦截非法登录用户
 */
@ControllerAdvice
public class MyControllerAdvice {

    //捕捉到的异常
    @ExceptionHandler(value = ExceptionUtil.class)
    public void handleServiceException(ExceptionUtil exception, HttpServletResponse response) {
        try {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.setStatus(exception.getCode());
            try (PrintWriter out = response.getWriter()) {
                out.print("{ \"code\": " + exception.getCode() + ", \"message\": \"" + exception.getMsg() + "\"}");
                out.flush();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {

        }
    }

}
