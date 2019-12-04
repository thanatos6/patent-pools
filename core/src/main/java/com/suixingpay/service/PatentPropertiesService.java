package com.suixingpay.service;

import com.suixingpay.pojo.PatentProperties;
import com.suixingpay.pojo.PatentPropertiesList;

import java.util.List;

/**
 * @author kongjian
 */
public interface PatentPropertiesService {
    /**
     * 通过专利属性id获取专利属性信息
     * @param id 专利属性id
     * @return 单条专利属性实体
     */
    PatentProperties searchPatentProperties(int id);

    /**
     * 通过专利属性名称获取专利属性信息列表
     * @param name 专利属性名
     * @return 专利属性实体列表
     */
    List<PatentProperties> searchPatentPropertiesByName(String name);

    /**
     * 根据专利id获取专利属性列表
     * @param id 专利id
     * @return 专利属性实体列表
     */
    List<PatentProperties> searchPatentPropertiesByPatentId(int id);

    /**
     * 存储专利属性内容
     * @param patentProperties 专利属性实体
     */
    void savePatentProperties(PatentProperties patentProperties);

    /**
     * 删除专利属性
     * @param patentProperties 专利属性实体
     */
    void removePatentProperties(PatentProperties patentProperties);

    /**
     * 根据专利属性名，联表查询专利属性对应的专利信息
     * @param name 专利属性名
     * @return 专利属性与专利信息组合而成的实体信息列表
     */
    List<PatentPropertiesList> searchPropertiesJoinPatent(String name);

    /**
     * 根据专利属性名或专利名，联表查询专利属性对应的专利信息，参数使用实体信息
     * @param patentPropertiesList 联表数据结果实体格式信息
     * @return 专利属性与专利信息组合而成的实体信息列表
     */
    List<PatentPropertiesList> searchPropertiesJoinPatentEntity(PatentPropertiesList patentPropertiesList);
}
