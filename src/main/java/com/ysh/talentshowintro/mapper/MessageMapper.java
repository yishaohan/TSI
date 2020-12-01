package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    @Insert("insert into `messages`(message) values(#{message})")
    public void save(Message message);
}
