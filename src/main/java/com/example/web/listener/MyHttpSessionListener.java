package com.example.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    public static int onlineNum = 0;

    public void sessionCreated(HttpSessionEvent se) {
        onlineNum++;
        System.out.println("onlineNum create, value : " + onlineNum);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        onlineNum--;
        System.out.println("onlineNum destroy, value : " + onlineNum);
    }
}
