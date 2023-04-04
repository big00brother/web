package com.example.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class UserListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("---------Listener初始化");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("---------Listener销毁");
    }
    
}
