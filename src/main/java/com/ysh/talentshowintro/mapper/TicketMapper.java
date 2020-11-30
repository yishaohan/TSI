package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Insert("insert into ticket(email, number) values(#{email}, #{number})")
    void save(Ticket ticket);
    @Select("select id, email, number from ticket where email=#{email}")
    List<Ticket> getTicketByEmail(String email);
    @Delete("delete from ticket where number=#{number}")
    void deleteTicket(String number);
}

