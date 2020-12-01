package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Insert("insert into ticket(orderID, number) values(#{orderID}, #{number})")
    void save(Ticket ticket);
    @Delete("delete from ticket where number=#{number}")
    void deleteTicket(String number);
    @Select("SELECT COUNT(*) from ticket")
    int getNum();
    @Select("SELECT * from ticket")
    List<Ticket> getTickets();
}

