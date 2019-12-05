package com.suixingpay.aspect;

import com.suixingpay.mapper.FileMapper;
import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

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


    public static String AGREE = "agree";
    public static String REJECT = "reject";
    public static String EDITED = "editPatentById";
    public static String LOGIN = "login";
    public static String UPLOAD = "upload";
    public static String CLAIM = "claim";
    public static String UPDATE = "update";

    @Autowired
    FileMapper fileMapper;

    @Autowired
    LogMapper logMapper;

    /**
     * 定义切点pointCutAgree，此注释所在的方法，即为AOP切面所修饰的方法
     */
    @Pointcut("@annotation(com.suixingpay.aspect.Action)")
    public void cutAnnotation() {

    }

    @After("cutAnnotation()")
    public void afterPointCutReject(JoinPoint joinPoint) {

        //从request域中，获取session，通过session获取user对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Date date = new Date();
        Log log = new Log();

        //使用getArgs()获取切点方法的入参，

        if (AGREE.equals(joinPoint.getSignature().getName())
                || EDITED.equals(joinPoint.getSignature().getName())) {
            Object[] args = joinPoint.getArgs();
            PatentInfo patentInfo = (PatentInfo) args[0];

            log.setPatentInfoId(patentInfo.getId());

            if (AGREE.equals(joinPoint.getSignature().getName())) {
                log.setMessage(user.getName() + "同意了ID为" + patentInfo.getId() + "的专利申请");
            } else if (EDITED.equals(joinPoint.getSignature().getName())) {
                log.setMessage(user.getName() + "修改了ID为" + patentInfo.getId() + "的专利");
            }

        } else if (LOGIN.equals(joinPoint.getSignature().getName())) {

            log.setPatentInfoId(0);
            log.setMessage(user.getName() + "登陆了系统");

        } else if (UPLOAD.equals(joinPoint.getSignature().getName())) {

            Object[] args = joinPoint.getArgs();
            Integer patentId = (Integer) args[0];
            MultipartFile multipartFile = (MultipartFile) args[1];

            log.setPatentInfoId(patentId);
            log.setMessage(user.getName() + "上传了文件,文件名为" + multipartFile.getOriginalFilename());

        } else if (REJECT.equals(joinPoint.getSignature().getName())) {

            Object[] args = joinPoint.getArgs();
            RejectContent rejectContent = (RejectContent) args[0];

            log.setPatentInfoId(rejectContent.getPatentId());
            log.setMessage(user.getName() + "驳回了了ID为" + rejectContent.getPatentId() + "的专利申请");
        } else if (CLAIM.equals(joinPoint.getSignature().getName())) {
            Object[] args = joinPoint.getArgs();
            PatentInfo patentInfo = (PatentInfo) args[0];

            log.setPatentInfoId(patentInfo.getId());
            log.setMessage(user.getName() + "认领了ID为" + patentInfo.getId() + "的专利申请");
        } else if (UPDATE.equals(joinPoint.getSignature().getName())) {

            System.out.println("删除方法执行了");
            Object[] args = joinPoint.getArgs();
            Integer filesId = (Integer) args[0];



            Files files =fileMapper.selectPathByFileId(filesId);
            log.setPatentInfoId(files.getFilePatentId());

            System.out.println(files.getFilePatentId()+" "+files.getFileName());
            log.setMessage(user.getName() + "删除了专利" + files.getFilePatentId() + "的文件,文件名为"+files.getFileName());

        }


        log.setUserId(user.getId());
        log.setCreateDate(date);
        log.setModifyDate(date);
        log.setIsDelete((byte) 0);

        logMapper.insert(log);
    }

}






