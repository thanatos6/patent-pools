package com.suixingpay.aspect;

import ch.qos.logback.core.joran.action.ActionConst;
import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.pojo.PatentInfo;
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

    @Autowired
    LogMapper logMapper;

    /*
    定义切点1，切入点1为com.suixingpay.controller下的所有函数
     */
    @Pointcut("execution(public * com.suixingpay.controller.*.*(..))")
    public void pointCut() {
    }

    /*
    定义切点2，切点2为编辑专利方法
     */
    @Pointcut("execution(public * com.suixingpay.controller.PatentInfoController.*(..))")
    public void pointCut1() {
    }


    @Pointcut("execution(public * com.suixingpay.controller.StatusCodeController.*(..))")
    public void pointCut2() {
    }



    @After("pointCut1()")
    public void after1(JoinPoint joinPoint){
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
        if ("editPatentById".equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"修改了专利");
        }else if ("addNewPatent".equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"新建了专利，专利ID为"+patentInfo.getId());
        }
        log.setCreateDate(date);
        log.setModifyDate(date);
        log.setIsDelete((byte)0);

        logMapper.insert(log);

    }

    @After("pointCut2()")
    public void after2(JoinPoint joinPoint){
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
        if ("agree".equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"同意了ID为"+patentInfo.getId()+"的专利申请");
        }else if ("reject".equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"驳回了ID为"+patentInfo.getId()+"的专利申请");
        }else if ("claim".equals(joinPoint.getSignature().getName())){
            log.setMessage(username+"认领了ID为"+patentInfo.getId()+"的专利");
        }

        log.setCreateDate(date);
        log.setModifyDate(date);
        log.setIsDelete((byte)0);

        logMapper.insert(log);

    }




}
