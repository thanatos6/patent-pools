package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import org.apache.ibatis.annotations.Mapper;

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
     * @return 模糊搜索专利的实体集合，如果为空就返回空
     */
    List<PatentInfo> selectPatentFuzzy(PatentInfo patentInfo);
}
