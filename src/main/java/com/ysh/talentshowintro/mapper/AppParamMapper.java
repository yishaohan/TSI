package com.ysh.talentshowintro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AppParamMapper {

    @Select("select value from appparam where `key`=#{key}")
    String getAppParamByKey(String key);

    @Update("update appparam set value=#{value} where `key`=#{key}")
    void setAppParamByKey(String key, String value);
}
