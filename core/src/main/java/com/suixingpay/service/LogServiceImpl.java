package com.suixingpay.service;

import com.suixingpay.mapper.LogMapper;
import com.suixingpay.pojo.Log;
import com.suixingpay.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hyx
 */

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public List<Log> selectLogById(Integer id) {

        return logMapper.selectLogById(id);
    }

    @Override
    public List<Log> selectAllLog() {

        return logMapper.selectAllLog();
    }
}
