package com.suixingpay.controller;


import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.service.LogService;
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
    LogService logService;



    @GetMapping("selectAllLog")
    public List selectAllLog(){

        List<Log> list = logService.selectAllLog();

        return list;
    }

    @RequestMapping("selectLogById")
    public List<Log> selectLogById(Integer id){

        List<Log> list = logService.selectLogById(id);

        return list;
    }




}
