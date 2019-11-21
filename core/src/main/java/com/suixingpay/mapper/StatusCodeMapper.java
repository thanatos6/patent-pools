package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:09
 */
@Mapper
public interface StatusCodeMapper {
    void updateStatusPass(int patentId);
    void updateStatusReject (int patentId);
    void updateStatusWriter (int patentId);
    //点击认领
    void updateStatusClaim (int patentId);
    //点击编辑完成
    void updateStatusFinish (int patentId);
}

