package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.MailQueue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MailQueueMapper {

    @Insert("insert into mailQueue(`from`, `to`, cc, bcc, subject, context, createDateTime) values(#{from}, #{to}, #{cc}, #{bcc}, #{subject}, #{context}, #{createDateTime})")
    void addMail(MailQueue mail);

    @Select("select id, `from`, `to`, cc, bcc, subject, context, createDateTime from mailqueue where status='Create' order by id limit 0,1")
    MailQueue getOldestMail();

    @Update("update mailqueue set status=#{status} where id=#{mailID}")
    void setMailQueueStatus(int mailID, String status);
}
