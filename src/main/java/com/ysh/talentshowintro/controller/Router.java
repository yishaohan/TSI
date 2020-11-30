package com.ysh.talentshowintro.controller;

import com.ysh.talentshowintro.service.ContestantService;
import com.ysh.talentshowintro.service.EmailService;
import com.ysh.talentshowintro.service.OrderService;
import com.ysh.talentshowintro.service.TicketService;
import com.ysh.talentshowintro.model.Contestant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Router {
    final TicketService ticketService;
    final ContestantService contestantService;
    final EmailService emailService;
    final OrderService orderService;

    public Router(TicketService ticketService, ContestantService contestantService, EmailService emailService, OrderService orderService) {
        this.ticketService = ticketService;
        this.contestantService = contestantService;
        this.emailService = emailService;
        this.orderService = orderService;
    }

    @PostMapping("/vote")
    public void getVote(HttpServletRequest req, HttpServletResponse resp) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String name = req.getParameter("vote" + i);
            if (name != null) {
                list.add(name);
            }
        }
        if (list.size() > 2) {
            try {
                resp.sendRedirect("/?error=vote greater than three!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (!emailService.getEmails().contains(req.getParameter("email"))) {
            try {
                resp.sendRedirect("/?error=email not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (emailService.isVoted(req.getParameter("email"))) {
            try {
                resp.sendRedirect("/?error=You already voted!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        emailService.vote(req.getParameter("email"));

        for (String s : list) {
            contestantService.voteByName(s);
        }

        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/admin")
    public ModelAndView getAdmin(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("admin");
        List<Contestant> contestants = contestantService.getContestants();
        mv.addObject("contestants", contestants);
        mv.addObject("sum", contestantService.getNumOfBallots());
        return mv;
    }

    @PostMapping("/ticket")
    public void getTicket(HttpServletRequest req, HttpServletResponse resp) {
        String num = ticketService.save(req.getParameter("email"));
        try {
            resp.sendRedirect("/?ticket=" + num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/captureOrder")
    public void captureOrder(HttpServletRequest req, HttpServletResponse resp) {
        orderService.captureOrder(req.getParameter("id"));
        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/orderError")
    public void orderError(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getParameter("message"));
        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

