package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentProperties;
import com.suixingpay.pojo.PatentPropertiesList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author kongjian
 */
@Mapper
public interface PatentPropertiesMapper {
    /**
     * 通过专利属性id获取专利属性信息
     * @param id 专利属性id
     * @return 单条专利属性实体
     */
    PatentProperties selectPatentPropertiesById(int id);

    /**
     * 通过专利属性名称获取专利属性信息列表
     * @param indicatorName 专利属性名
     * @return 专利属性实体列表
     */
    List<PatentProperties> selectPatentPropertiesByName(String indicatorName);

    /**
     * 插入专利属性内容
     * @param patentProperties 专利属性实体
     */
    void insertPatentProperty(PatentProperties patentProperties);

    /**
     * 假删除专利属性，更新is_delete字段
     * @param patentProperties 专利属性实体
     */
    void deletePatentProperty(PatentProperties patentProperties);

    /**
     * 根据专利id获取专利属性列表
     * @param id 专利id
     * @return 专利属性实体列表
     */
    List<PatentProperties> selectPatentPropertiesByPatentId(int id);

    /**
     * 根据专利属性名，联表查询专利属性对应的专利信息
     * @param indicatorName 专利属性名
     * @return 专利属性与专利信息组合而成的实体信息列表
     */
    List<PatentPropertiesList> selectPropertiesJoinPatent(String indicatorName);

    /**
     * 联表查询专利属性对应的专利信息，参数使用实体信息
     * @param patentPropertiesList 联表数据结果实体格式信息
     * @return 专利属性与专利信息组合而成的实体信息列表
     */
    List<PatentPropertiesList> selectPropertiesJoinPatentEntity(PatentPropertiesList patentPropertiesList);
}
