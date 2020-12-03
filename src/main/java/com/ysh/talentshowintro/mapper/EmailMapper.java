package com.ysh.talentshowintro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmailMapper {
    @Select("Select email from emails")
    List<String> getAllEmails();

    @Select("Select voted from emails where email=#{email}")
    boolean isVoted(String email);

    @Update("Update emails set voted=true where email=#{email}")
    void vote(String email);
}

