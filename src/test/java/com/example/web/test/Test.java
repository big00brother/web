package com.example.web.test;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    static A a = new A();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("t1:" + Thread.currentThread().getId());
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        };
        t1.start();
//        t1.join();

//        synchronized (a) {
//            System.out.println(ClassLayout.parseInstance(a).toPrintable());
//        }

    }
}
class A {
//    boolean a = true;
    int a = 1;
}