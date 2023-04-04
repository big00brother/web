package com.example.web.configuration;

import com.example.web.filter.MyFilter;
import com.example.web.filter.MyOnceProRequestFilter;
import com.example.web.listener.MyHttpSessionListener;
import com.example.web.listener.UserListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration  //表示这是一个设置类
public class MyRegistration {

    @Bean
    public FilterRegistrationBean MyRegistrationBean(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyOnceProRequestFilter());   //设置过滤器
        registrationBean.setUrlPatterns(Collections.singleton("/*")); //设置过滤路径
        registrationBean.setOrder(1);  //设置优先级
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean MyFilterBean(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());   //设置过滤器
        registrationBean.setUrlPatterns(Collections.singleton("/*")); //设置过滤路径
        registrationBean.setOrder(2);  //设置优先级
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean servletContextListenerRegister() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new UserListener());
        return srb;
    }

    @Bean
    public ServletListenerRegistrationBean  httpSessionListenerRegister() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        return srb;
    }
}
