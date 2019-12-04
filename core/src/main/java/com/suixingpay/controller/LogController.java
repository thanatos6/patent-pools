package com.suixingpay.controller;


import com.suixingpay.aspect.Action;
import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String,Object> selectUserLog(Integer id){
        Map<String, Object> map =new HashMap<>(0);
        List<Log> list = logService.selectUserLog(id);
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

    @RequestMapping("test")
    public String test(){

        return "注解执行了";
    }
}
