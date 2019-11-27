package com.suixingpay.service;

import com.suixingpay.pojo.PatentInfo;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:14
 */
public interface StatusCodeService {

    /**
     *  点击同意，根据专利Id号，点击同意按钮，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    String updateStatusPass(int patentID);


    /**
     *  点击驳回，根据专利Id号，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    String updateStatusReject(int patentID);


    /**
     *  点击重新编写，根据专利Id号，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    boolean updateStatusWriter(int patentID);


    /**
     *  点击认领该状态，根据专利Id号，改变流程状态
     *
     * @param patentInfo 专利对象
     * @return
     */
    String updateStatusClaim(PatentInfo patentInfo);


    /**
     *  点击编写完成，根据专利Id号，改变流程状态
     *
     * @param patentId 专利ID号
     * @return
     */
    boolean updateStatusFinish(int patentId);


    /**
     * 根据角色码查找有权限的待办
     *
     * @param userId 当前登录用户的ID号
     * @param role   当前登录用户的权限号，管理员为1，撰写人为0
     * @return
     */
    String selectCodeByRole(int role,int userId);


}
