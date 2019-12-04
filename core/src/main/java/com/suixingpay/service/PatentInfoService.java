package com.suixingpay.service;


import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.User;

import java.util.List;


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
     * @param editUserId 编辑的用户 id
     * @return 返回一个状态 Json 串。{status :  value}.成功返回：value = success;失败返回：value = error
     */
    String editPatent(PatentInfo patentInfo, Integer editUserId);

    /**
     * 返回已经被领取的专利池专利实体集合
     * @param user 用户实体
     * @return 返回一个 JSON 串。返回: JSON 串 ：List<PatentInfo> , 如果查不到或失败返回 failed 状态信息
     */
    String searchNavigationInfoReceive(User user);

    /**
     * 返回未被领取的专利池专利实体集合
     * @param user 用户实体
     * @return 返回一个 JSON 串。返回: JSON 串 ：List<PatentInfo> , 如果查不到或失败返回 failed 状态信息
     */
    String searchNavigationInfoNoReceive(User user);

    /**
     * 返回指定用户类型的已经认领的专利
     *
     * @param patentInfo 专利实体
     * @param user         用户 id
     * @return 返回一个 JSON 串。返回: JSON 串 ：List<PatentInfo> , 如果查不到则返回 null 信息或者 failed 信息
     */
    String searchPatentByUserAndReceive(PatentInfo patentInfo, User user);

    /**
     * 返回指定用户类型的未被认领的专利
     *
     * @param patentInfo 专利实体
     * @param user         用户 id
     * @return 返回一个 JSON 串。返回: JSON 串 ：List<PatentInfo> , 如果查不到则返回 null 信息或者 failed 信息
     */
    String searchPatentByUserAndNoReceive(PatentInfo patentInfo, User user);

    /**
     * 根据状态码来返回指定集合状态码集合里的专利，id 如果为空，就返回全部专利，不为空则还有 认领者 owner_user_id 的返回限制
     *
     * @param statusList 状态码集合
     * @param userId     用户 id
     * @return 返回指定集合状态码集合的专利
     */
    String searchPatentByCurrentStatusList(List<Integer> statusList, Integer userId);


}
