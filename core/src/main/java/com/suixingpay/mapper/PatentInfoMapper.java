package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 詹文良
 * @program: patent-pool-3th
 * @description: 专利信息表的 mapper 接口
 * <p>
 * Created by jalr on 2019/11/21.
 */

@Mapper
public interface PatentInfoMapper {
    /**
     * 插入专利信息服务
     *
     * @param patentInfo 专利信息实体
     * @return 0 插入失败，1 插入成功
     */
    Integer insertPatentInfo(PatentInfo patentInfo);

    /**
     * 模糊查询专利专利，为搜索专利提供的服务
     *
     * @param patentInfo 专利信息实体
     * @return 模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentFuzzy(PatentInfo patentInfo);

    /**
     * 根据专利的 id, 更新专利的各种信息
     *
     * @param patentInfo 专利信息实体
     * @return 0 表示更新失败, 1 表示更新成功
     */
    Integer updatePatentInfoById(PatentInfo patentInfo);


    /**
     * 普通用户的专利搜索页初始化，就把 owner_id 为该用户的专利记录和 owner_id 为空的记录返回
     * owner_id 已经存在的排在前面
     * owner_id 没有的排在后面
     *
     * @param ownerUserId
     * @return 模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentNormalUser(@Param("ownerUserId") Integer ownerUserId);


    /**
     * 管理员的专利搜索页初始化，信息全部返回, 并做一个排序, 直接按照修改时间倒序排 ASC
     *
     * @return 模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentRootUser();

    /**
     * 普通用户的专利搜索页的条件模糊搜索，就把 owner_id 为该用户的专利记录和 owner_id 为空的记录返回
     * owner_id 已经存在的排在前面
     * owner_id 没有的排在后面
     *
     * @param patentInfo 专利信息实体
     * @return 模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentNormalUserCondition(PatentInfo patentInfo);


    /**
     * 管理员的专利搜索页的条件模糊搜索，信息全部返回, 并做一个排序, 直接按照修改时间倒序排 ASC
     *
     * @param patentInfo 专利信息实体
     * @return 模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentRootUserCondition(PatentInfo patentInfo);

    /**
     * 传入指定的状态码集合以及用户 id 来查询该范围内的专利信息记录，这是为张提供的服务 mapper
     *
     * @param currentStatusList 传入的状态码集合
     * @param userId            转入的用户 id
     * @return 搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentByStatusList(@Param("currentStatusList") List<Integer> currentStatusList,
                                              @Param("userId") Integer userId);
}
