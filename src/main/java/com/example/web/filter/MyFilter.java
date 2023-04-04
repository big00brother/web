package com.example.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Component
//@WebFilter(filterName="myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("加载过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("myFilter start.");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("myFilter end.");
    }

    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
