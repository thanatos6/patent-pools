package com.suixingpay.service;

import com.suixingpay.pojo.Log;

import java.util.List;

/**
 * @author hyx
 */
public interface LogService {


    List<Log> selectLogById(Integer id);

    List<Log> selectAllLog();

}
