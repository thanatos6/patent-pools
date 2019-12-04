package com.suixingpay.service;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.RejectContent;


/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:14
 */
public interface StatusCodeService {

    /**
     * 点击同意，根据专利Id号，点击同意按钮，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    String updateStatusPass(int patentID);


    /**
     * 点击认领该状态，根据专利Id号，改变流程状态
     *
     * @param patentInfo 专利对象
     * @return
     */
    String updateStatusClaim(PatentInfo patentInfo);


    /**
     * 点击编写完成，根据专利Id号，改变流程状态
     *
     * @param patentInfo 专利实体
     * @return
     */
    boolean updateStatusFinish(PatentInfo patentInfo);


    /**
     * 根据角色码查找有权限的待办
     *
     * @param userId 当前登录用户的ID号
     * @param role   当前登录用户的权限号，管理员为1，撰写人为0
     * @return
     */
    String selectCodeByRole(int role, int userId);


    /**
     * 根据认领人ID查看已认领未撰写的专利 ，状态码为4
     *
     * @param patentInfo 当前登录用户的ID号
     * @return
     */
    String selectPatentByclaimed(PatentInfo patentInfo);


    /**
     * 根据认领人ID查看自己认领的待审批的专利 ，状态码为1,5,6
     *
     * @param patentInfo 当前登录用户的ID号
     * @return
     */
    String selectPatentByWaitMyself(PatentInfo patentInfo);


    /**
     * 根据当前用户ID查看自己认领的待维护的专利列表 ，状态码为7提交成功
     *
     * @param patentInfo 当前登录用户的ID号
     * @return
     */
    String selectPatentBySuccess(PatentInfo patentInfo);


    /**
     * 管理员点击驳回按钮，改变专利当前状态，并且插入驳回列表一条信息
     *
     * @param rejectContent 驳回列表实体
     * @return
     */
    String createNewReject(RejectContent rejectContent);


    /**
     * 撰写人点击查看驳回原因，显示专利被驳回理由
     *
     * @param patentId 专利ID号
     * @return
     */
    String selectPatentViewReason(int patentId);
}
