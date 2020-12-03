package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.TicketMapper;
import com.ysh.talentshowintro.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    final TicketMapper ticketMapper;

    public TicketService(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public void save(Ticket ticket) {
        ticketMapper.save(ticket);
    }

//    public void deleteTicket(String number) {
//        ticketMapper.deleteTicket(number);
//    }

    public int getAllTicketsCount() {
        return ticketMapper.getAllTicketsCount();
    }

    public Ticket getTicketByID(int id) {
        return ticketMapper.getTicketByID(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

//    public Ticket getRandom() {
//        return getTickets().get((int) (Math.random() * getNum()));
//    }
}

