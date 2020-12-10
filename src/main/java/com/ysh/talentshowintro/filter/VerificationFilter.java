package com.ysh.talentshowintro.filter;

import com.ysh.talentshowintro.service.AppParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class VerificationFilter extends GenericFilterBean {
    private final String defaultFilterProcessUrl = "/vote";
    final AppParamService appParamService;

    public VerificationFilter(AppParamService appParamService) {
        this.appParamService = appParamService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) &&
                defaultFilterProcessUrl.contains(request.getServletPath()) && appParamService.getAppParamByKey(
                        "enableVerificationCode").equals("true")) {
            // 验证码验证
            String requestCaptcha = request.getParameter("verifyCode");
            String genCaptcha = (String) request.getSession().getAttribute("verifyCode");
            String uri = request.getRequestURI();
            if (requestCaptcha == null || requestCaptcha.equals("")) {
                response.sendRedirect("/?error=verification code must be filled!");
            } else if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                response.sendRedirect("/?error=Verification code error!");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}

