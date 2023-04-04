package com.example.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName="myOnceProRequestFilter",urlPatterns = "/*") //继承OncePerRequestFilter的过滤的类，对每个请求只会过滤一次，这也是与实现Filter类不一样的地方
//@Component
public class MyOnceProRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("myOnceProRequestFilter start.");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        System.out.println("myOnceProRequestFilter end.");
    }
}
