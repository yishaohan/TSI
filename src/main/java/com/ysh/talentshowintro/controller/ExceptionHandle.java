package com.ysh.talentshowintro.controller;

import com.ysh.talentshowintro.model.Logs;
import com.ysh.talentshowintro.service.LogsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@ControllerAdvice
public class ExceptionHandle {

    final LogsService logsService;

    public ExceptionHandle(LogsService logsService) {
        this.logsService = logsService;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("timestamp", new Timestamp(System.currentTimeMillis()));
        mv.addObject("status", String.valueOf(resp.getStatus()));
        mv.addObject("path", req.getRequestURI());
        mv.addObject("exception", e);
        mv.setViewName("exception");
        //////////////////////////////////////////////////////////////
        Logs logs = new Logs();
        String temp;
        logs.setRequestURI(req.getRequestURI());
        temp = req.getQueryString();
        if (temp == null) {
            temp = "";
        }
        logs.setQueryString(temp);
        logs.setMethod(req.getMethod());
        logs.setStatus(String.valueOf(resp.getStatus()));
//        temp = "";
//        if (e.toString().length() > 4000) {
//            temp = e.toString().substring(0, 3999);
//        }
        logs.setMessage(e.toString());
        temp = req.getHeader("referer");
        if (temp == null) {
            temp = "";
        }
        logs.setReferer(temp);
        logs.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
        logs.setThread(Thread.currentThread().getName());
        logs.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        logs.setRemoteAddr(req.getRemoteAddr());
        logs.setRemotePort(String.valueOf(req.getRemotePort()));
        logs.setUserAgent(req.getHeader("User-Agent"));
        logs.setAcceptLanguage(req.getHeader("Accept-Language"));
        temp = req.getRequestedSessionId();
        if (temp == null) {
            temp = "";
        }
        logs.setSessionId(temp);
        temp = resp.getContentType();
        if (temp == null) {
            temp = "";
        }
        logs.setContentType(temp);
        logs.setConsumeTime(-1);
        logs.setSource("ExceptionHandle");
        logsService.save(logs);
        //////////////////////////////////////////////////////////////
        return mv;
    }
}
