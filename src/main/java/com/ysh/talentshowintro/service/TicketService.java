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

    public String save(String orderID) {
        Ticket ticket = new Ticket();
        ticket.setOrderID(orderID);
        Random random = new Random();
        String num = Integer.toString(random.nextInt());
        num = num.substring(1, 7);
        ticket.setNumber(num);
        System.out.println(num);
        ticketMapper.save(ticket);
        return num;
    }

    public void deleteTicket(String number) {
        ticketMapper.deleteTicket(number);
    }

    public int getNum() {
        return ticketMapper.getNum();
    }

    private List<Ticket> getTickets() {
        return ticketMapper.getTickets();
    }

    public Ticket getRandom() {
        return getTickets().get((int)(Math.random()*getNum()));
    }
}

