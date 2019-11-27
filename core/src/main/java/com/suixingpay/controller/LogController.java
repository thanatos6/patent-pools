package com.suixingpay.controller;


import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author hyx
 */
@RestController
public class LogController {

    @Autowired
    LogMapper logMapper;

    @GetMapping("selectAllLog")
    public List selectAllLog(){

        List<Log> list = logMapper.selectAllLog();
        System.out.println(list);
        return list;
    }

    @RequestMapping("selectLogById")
    public List<Log> selectLogById(Integer id){

        System.out.println(logMapper.selectLogById(id));

        List<Log> list = logMapper.selectLogById(id);
        return list;
    }

//    @RequestMapping("addLog")
//    public int addLog(){
//
//        Date date=new Date();
//        Log log = new Log();
//
//        log.setUserId(1002);
//        log.setPatentInfoId(1002);
//        log.setMessage("我在测试");
//        log.setCreateDate(date);
//        log.setModifyDate(date);
//
//
//
//        return logMapper.insert(log);
//    }


}
