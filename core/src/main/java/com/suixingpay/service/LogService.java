package com.suixingpay.service;

import com.suixingpay.pojo.Log;

import java.util.List;

/**
 * @author hyx
 */
public interface LogService {

    /**
     *提供根据ID查询日志服务
     *
     * @param id
     * @return List<Log>
     *
     */
    List<Log> selectLogById(Integer id);

    /**
     *提供查询全部日志服务
     * @return List<Log>
     *
     */
    List<Log> selectAllLog();

}
