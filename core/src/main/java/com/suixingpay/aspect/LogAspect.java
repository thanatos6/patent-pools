package com.suixingpay.aspect;

import ch.qos.logback.core.joran.action.ActionConst;
import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.RejectContent;
import com.suixingpay.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;


/**
 * @author hyx
 * 切面实现日志功能
 */
@Aspect
@Component
public class LogAspect {

    public static String AGREE="agree";
    public static String REJECT="reject";
    public static String EDITPATENTBYID="editPatentById";


    @Autowired
    LogMapper logMapper;


    /**定义切点pointCutAgree，切点pointCutAgree为管理员同意申请的方法*/
    @Pointcut("execution(public * com.suixingpay.controller.StatusCodeController.agree(..))")
    public void pointCutAgree() {
    }

    /**定义切点pointCutReject，切入点pointCutReject为管理员驳回申请的方法*/
    @Pointcut("execution(public * com.suixingpay.controller.StatusCodeController.reject(..))")
    public void pointCutReject() {
    }

    /**定义切点pointCutEditPatentById，切点pointCutEditPatentById为编辑专利方法*/
    @Pointcut("execution(public * com.suixingpay.controller.PatentInfoController.editPatentById(..))")
    public void pointCutEditPatentById() {
    }





    @After("pointCutEditPatentById()")
    public void afterPointCutEditPatentById(JoinPoint joinPoint){
        /*
        从request域中，获取session，通过session获取user对象
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*
        使用getArgs()获取切点方法的入参，
         */
        Object[] args = joinPoint.getArgs();
        PatentInfo patentInfo = (PatentInfo)args[0];


        Date date=new Date();
        Log log = new Log();
        log.setPatentInfoId(patentInfo.getId());
        log.setUserId(user.getId());
        String username = user.getName();
        if (EDITPATENTBYID.equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"修改了ID为"+patentInfo.getId()+"的专利");
        }
        log.setCreateDate(date);
        log.setModifyDate(date);
        log.setIsDelete((byte)0);

        logMapper.insert(log);

    }

    @After("pointCutAgree()")
    public void afterPointCutAgree(JoinPoint joinPoint){

        //从request域中，获取session，通过session获取user对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //使用getArgs()获取切点方法的入参，
        Object[] args = joinPoint.getArgs();
        RejectContent patentInfo = (RejectContent)args[0];

        Date date=new Date();
        Log log = new Log();
        log.setPatentInfoId(patentInfo.getId());
        log.setUserId(user.getId());
        String username = user.getName();
        if (AGREE.equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"同意了ID为"+patentInfo.getId()+"的专利申请");
        }

        log.setCreateDate(date);
        log.setModifyDate(date);
        log.setIsDelete((byte)0);

        logMapper.insert(log);

    }


    /*@After("pointCutReject()")
    public void afterPointCutReject(JoinPoint joinPoint){


        //从request域中，获取session，通过session获取user对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        //使用getArgs()获取切点方法的入参，
        Object[] args = joinPoint.getArgs();
        PatentInfo patentInfo = (PatentInfo)args[0];


        Date date=new Date();
        Log log = new Log();
        log.setPatentInfoId(patentInfo.getId());
        log.setUserId(user.getId());
        String username = user.getName();
        if (REJECT.equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"驳回了了ID为"+patentInfo.getId()+"的专利申请");
        }

        log.setCreateDate(date);
        log.setModifyDate(date);
        log.setIsDelete((byte)0);
        logMapper.insert(log);

    }*/
}





