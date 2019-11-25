package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentProperties;
// import com.suixingpay.pojo.PatentPropertiesList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PatentPropertiesMapper {
    PatentProperties selectPatentPropertiesById(int id);

    List<PatentProperties> selectPatentPropertiesByName(String indicatorName);

    void insertPatentProperty(PatentProperties patentProperties);

    void deletePatentProperty(PatentProperties patentProperties);

    List<PatentProperties> selectPatentPropertiesByPatentId(int id);
    // List<PatentPropertiesList> selectPatentByPropertiesName(PatentPropertiesList patentPropertiesList)
}
