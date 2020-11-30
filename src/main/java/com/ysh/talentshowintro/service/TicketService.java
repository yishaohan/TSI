package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.TicketMapper;
import com.ysh.talentshowintro.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TicketService {
    final TicketMapper ticketMapper;

    public TicketService(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public String save(String email) {
        Ticket ticket = new Ticket();
        ticket.setEmail(email);
        Random random = new Random();
        String num = Integer.toHexString(random.nextInt());
        num = num.substring(0, 6);
        ticket.setNumber(num);
        System.out.println(num);
        ticketMapper.save(ticket);
        return num;
    }

    public List<Ticket> getTicketByEmail(String email) {
        return ticketMapper.getTicketByEmail(email);
    }

    public void deleteTicket(String number) {
        ticketMapper.deleteTicket(number);
    }
}

