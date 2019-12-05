package com.suixingpay.controller;


import com.suixingpay.aspect.Action;
import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.pojo.User;
import com.suixingpay.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author hyx
 */
@RestController
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("selectAllLog")
    public Map<String,Object> selectAllLog(){
        Map<String, Object> map =new HashMap<>(0);
        List<Log> list = logService.selectAllLog();
        Collections.reverse(list);
        if(list.size()==0){
            map.put("list","");
            map.put("status",1);
        }else{
            map.put("list",list);
            map.put("status",0);
        }
        return map;
    }


    @RequestMapping("selectLogById")
    public Map<String,Object> selectLogById(Integer id){
        Map<String, Object> map =new HashMap<>(0);
        if (id==null){

        }
        List<Log> list = logService.selectLogById(id);
        Collections.reverse(list);
        if(list.size()==0){
            map.put("list","");
            map.put("status",1);
        }else{
            map.put("list",list);
            map.put("status",0);
        }
        return map;
    }


    @RequestMapping("selectUserLog")
    public Map<String,Object> selectUserLog(){
        //从request域中，获取session，通过session获取user对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Map<String, Object> map =new HashMap<>(0);
        List<Log> list = logService.selectUserLog(user.getId());
        Collections.reverse(list);
        if(list.size()==0){
            map.put("list","");
            map.put("status",1);
        }else{
            map.put("list",list);
            map.put("status",0);
        }
        return map;
    }

}
