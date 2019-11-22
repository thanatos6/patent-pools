package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:09
 */
@Mapper
public interface StatusCodeMapper {
    void updateStatusPass(int patentId);

    void updateStatusReject(int patentId);

    void updateStatusWriter(int patentId);

    //点击认领
    void updateStatusClaim(PatentInfo patentInfo);

    //点击编辑完成
    void updateStatusFinish(int patentId);

    //根据角色码查找有权限的待办
    List<StatusCode> selectCodeByRole(int role);
}

