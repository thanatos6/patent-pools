package com.suixingpay.service;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:14
 */
public interface StatusCodeService {
    //点击同意
    boolean updateStatusPass(int patentID);

    //点击驳回
    boolean updateStatusReject(int patentID);

    //点击重新编写
    boolean updateStatusWriter(int patentID);

    //认领该状态
    boolean updateStatusClaim(PatentInfo patentInfo);

    //点击编写完成
    boolean updateStatusFinish(int patentId);

    //根据角色码查找有权限的待办
    List<StatusCode> selectCodeByRole(int role);
}
