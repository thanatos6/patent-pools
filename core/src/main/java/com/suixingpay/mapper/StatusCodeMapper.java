package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.RejectContent;
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

    //一审批驳回
    void updateStatusTalk(int patentId);

    //点击认领
    void updateStatusClaim(PatentInfo patentInfo);

    //点击编辑完成
    void updateStatusFinish(PatentInfo patentInfo);

    //根据角色码查找有权限的待办
    List<StatusCode> selectCodeByRole(int role);

    //根据专利ID号,查找当前专利状态码
    int selectCodeByPid(int patentId);

    //根据认领人ID查看已认领未撰写的专利 ，状态码为4
    List<PatentInfo> selectPatentByclaimed(PatentInfo patentInfo);

    //根据认领人ID查看自己认领的待审批的专利 ，状态码为1,5,6
    List<PatentInfo> selectPatentByWaitMyself(PatentInfo patentInfo);

    //根据当前用户ID查看自己认领的待维护的专利列表 ，状态码为7提交成功
    List<PatentInfo> selectPatentBySuccess(PatentInfo patentInfo);

    /**
     * 插入专利的驳回原因
     *
     * @param rejectContent 专利信息实体
     * @return 0 插入失败，1 插入成功
     */
    Integer insertRejectContent(RejectContent rejectContent);


    /**
     * 根据当前专利ID号查看自己认领的被驳回专利的原因
     *
     * @param patentId 专利ID号
     * @return List列表
     */
    List<RejectContent> selectPatentViewReason(int patentId);
}

