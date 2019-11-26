package com.suixingpay.aspect;

import ch.qos.logback.core.joran.action.ActionConst;
import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.pojo.PatentInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    LogMapper logMapper;


    //    /*
//    定义切点，切入点为com.suixingpay.controller下的所有函数
//     */
    @Pointcut("execution(public * com.suixingpay.controller.*.*(..))")
    public void Pointcut() {

    }

    @Pointcut("execution(public * com.suixingpay.controller.PatentInfoController.*(..))")
    public void Pointcut1() {

    }

    @Before("Pointcut1()")
    public void Before(JoinPoint joinPoint){

//        System.out.println("Before执行了");
//        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
//        System.out.println("目标方法所属类的简单类名:" +  joinPoint.getSignature().getDeclaringType().getSimpleName());
//        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
//        System.out.println("=================="+joinPoint.getSignature());
//

//        System.out.println("被代理的对象:" + joinPoint.getTarget());
//        System.out.println("代理对象自己:" + joinPoint.getThis());

        //获取传入目标方法的参数
        System.out.println("切面执行了。。。。。。。。。。。。。。。。。。");

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }

        PatentInfo patentInfo = (PatentInfo)args[0];


        Date date=new Date();

        Log log = new Log();

        log.setPatentInfoId(patentInfo.getId());

        if ("editPatentById".equals(joinPoint.getSignature().getName())){
                log.setMessage("编辑了专利");
        }
        log.setCreateDate(date);
        log.setModifyDate(date);

        logMapper.insert(log);

//
//        log.setUserId(1002);
//        } else if ("addLog".equals(joinPoint.getSignature().getName())){
//            log.setMessage("增加了日志");
//        } else if ("addLog".equals(joinPoint.getSignature().getName())){
//
//        } else if ("addLog".equals(joinPoint.getSignature().getName())){
//
//        } else if ("addLog".equals(joinPoint.getSignature().getName())){
//
//        }
//

//



    }

}
