package com.suixingpay.interceptor;

import com.suixingpay.util.ExceptionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/04 23:32
 *  用于校验拦截非法登录用户
 */
@Component public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {

        // 判断session值是否为空
        String requestUri = request.getRequestURI();
        if (null == request.getSession().getAttribute("user")) {
            // 抛出异常，用于全局捕捉
            throw new ExceptionUtil("未登陆或者登录失效", 401);
        } else {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void setCorsMappings(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

}
