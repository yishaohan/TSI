package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Insert("insert into ticket(owner, orderID, number, createDateTime, `show`) values(#{owner}, #{orderID}, #{number}, #{createDateTime}, #{show})")
    void save(Ticket ticket);

//    @Delete("delete from ticket where number=#{number}")
//    void deleteTicket(String number);

    @Select("select count(*) from ticket")
    int getAllTicketsCount();

    @Select("select id, owner, orderID, number, createDateTIme, `show` from ticket where id=#{id}")
    public Ticket getTicketByID(int id);

    @Select("select id, owner, orderID, number, createDateTIme, `show` from ticket")
    List<Ticket> getAllTickets();
}

