package com.suixingpay.service;


import com.suixingpay.pojo.PatentInfo;

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


}
