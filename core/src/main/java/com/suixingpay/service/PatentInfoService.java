package com.suixingpay.service;


import com.suixingpay.pojo.PatentInfo;


/**
 * @author 詹文良
 * @program: patent-pool-3th
 * @description: 专利信息的业务逻辑服务
 * <p>
 * Created by Jalr4ever on 2019/11/21.
 */

public interface PatentInfoService {

    /**
     * 传入专利实体，按照指定的专利实体创建
     *
     * @param patentInfo 专利实体
     * @return 返回一个状态 Json 串。{status :  value}.成功返回：value = success;失败返回：value = error
     */
    String createNewPatent(PatentInfo patentInfo);

    /**
     * 传入专利实体，按照部分条件做模糊搜索
     *
     * @param patentInfo 专利实体
     * @return 返回一个 JSON 串。成功返回: JSON 串 ：List<PatentInfo>;失败返回: status : error
     */
    String searchPatentAnyCondition(PatentInfo patentInfo);


    /**
     * 传入专利实体, 按照认领人的 id (用户 id)来更新一个专利各种信息
     *
     * @param patentInfo 专利实体
     * @return 返回一个状态 Json 串。{status :  value}.成功返回：value = success;失败返回：value = error
     */
    String editPatent(PatentInfo patentInfo);

    /**
     * 传入专利实体, 按照专利的 id (用户 id)来更新一个专利的认领人 id
     *
     * @param patentInfo 专利实体
     * @return 返回一个状态 Json 串。{status :  value}.成功返回：value = success;失败返回：value = error
     */
    String receivePatent(PatentInfo patentInfo);


    /**
     * 传入用户 id ，来显示专利导航页的东西，也就是当前用户能看到的专利池的专利
     *
     * @param id 用户 id
     * @return 返回一个 JSON 串。返回: JSON 串 ：List<PatentInfo> , 如果差不多则返回一个空的 LIST
     */
    String searchNavigationInfo(int id);

}
