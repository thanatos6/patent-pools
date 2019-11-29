package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:09
 */
@Mapper
public interface StatusCodeMapper {
    //审批同意
    void updateStatusPass(int patentId);

    //审批驳回
    void updateStatusReject(int patentId);

    //点击认领
    void updateStatusClaim(PatentInfo patentInfo);

    //点击编辑完成
    void updateStatusFinish(PatentInfo patentInfo);

    //根据角色码查找有权限的待办
    List<StatusCode> selectCodeByRole(int role);

    //根据专利ID号,查找当前专利状态码
    int selectCodeByPid(int patentId);
}

