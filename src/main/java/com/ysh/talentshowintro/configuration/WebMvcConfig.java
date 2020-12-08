package com.ysh.talentshowintro.configuration;

import com.ysh.talentshowintro.interceptor.Request;
import com.ysh.talentshowintro.service.LogsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    final LogsService logsService;

    public WebMvcConfig(LogsService logsService) {
        this.logsService = logsService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/test").setViewName("index");
        registry.addViewController("/error").setViewName("error");
//        registry.addViewController("/admin/draw").setViewName("ticketDraw");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Request(logsService))
                .excludePathPatterns("/bootstrap/**")
                .excludePathPatterns("/image/**")
                .excludePathPatterns("/favicon.ico");
//        registry.addInterceptor(new VerifyCodeRegister()).addPathPatterns("/register");
    }

}


