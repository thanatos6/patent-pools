package com.suixingpay.aspect;

import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * @author hyx
 * 切面实现日志功能
 */
@Aspect
@Component
@Slf4j
public class LogAspect {


    public static String FILE="FileController";
    public static String LOGIN="LoginController";
    public static String STATUS = "StatusCodeController";
    public static String PATENTINFO="PatentInfoController";

    public static String AGREE="agree";
    public static String REJECT="reject";
    public static String EDITPATENTBYID="editPatentById";



    @Autowired
    LogMapper logMapper;

    /**定义切点pointCutAgree，此注释所在的方法，即为AOP切面所修饰的方法*/
    @Pointcut("@annotation(com.suixingpay.aspect.Action)")
    public void CutAnnotation() {

    }

    @After("CutAnnotation()")
    public void afterPointCutReject(JoinPoint joinPoint){

        //从request域中，获取session，通过session获取user对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        //使用getArgs()获取切点方法的入参，
        if (joinPoint.getSignature().getDeclaringType().getSimpleName()==STATUS&&joinPoint.getSignature().getDeclaringType().getSimpleName()==PATENTINFO){
            if(joinPoint.getSignature().getDeclaringType().getSimpleName()==PATENTINFO){
                Object[] args = joinPoint.getArgs();
                PatentInfo patentInfo = (PatentInfo)args[0];
            }

            Object[] args = joinPoint.getArgs();
            PatentInfo patentInfo = (PatentInfo)args[0];


            Date date=new Date();
            Log log = new Log();
            log.setPatentInfoId(patentInfo.getId());
            log.setUserId(user.getId());
            log.setCreateDate(date);
            log.setModifyDate(date);
            log.setIsDelete((byte)0);

            if (REJECT.equals(joinPoint.getSignature().getName())){
                log.setMessage(user.getName()+"驳回了了名字为"+patentInfo.getId()+"的专利申请");
            } else if (AGREE.equals(joinPoint.getSignature().getName())){
                log.setMessage(user.getName()+"同意了名字为"+patentInfo.getId()+"的专利申请");
            } else if (EDITPATENTBYID.equals(joinPoint.getSignature().getName())){
                log.setMessage(user.getName()+"修改了名字为"+patentInfo.getId()+"的专利");
            }

            logMapper.insert(log);
        } else if(joinPoint.getSignature().getDeclaringType().getSimpleName()==LOGIN){
            Date date=new Date();
            Log log = new Log();

            log.setUserId(user.getId());
            log.setCreateDate(date);
            log.setModifyDate(date);
            log.setIsDelete((byte)0);
            log.setMessage(user.getName()+"登陆了系统");

        }
    }

    @Before("CutAnnotation()")
    public void AroundTest(JoinPoint joinPoint){

        System.out.println(joinPoint.getSignature().getName()+"方法执行了");
    }
}





