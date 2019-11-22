package com.suixingpay.service.Impl;


import com.suixingpay.mapper.StatusMapper;
import com.suixingpay.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusImpl {

    @Autowired
    StatusMapper statusMapper;


    public int statusById(int id){
        return statusMapper.selectStatusById(id);
    }




}
