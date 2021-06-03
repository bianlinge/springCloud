package com.dove.thread.ThreadLocal;

import javafx.scene.control.TextInputControl;

public class ThreadLcoalExample {

    private static ThreadLocal threadLocal = new ThreadLocal<String>();
    public static void main(String[] args) {

        //线程A
        Thread threadA = new Thread(()->{
            threadLocal.set("ThreadA：" + Thread.currentThread().getName());
            System.out.println("线程A本地变量中的值为：" + threadLocal.get());

            threadLocal.remove();
            System.out.println("线程A删除本地变量后ThreadLocal中的值为：" + threadLocal.get());
        },"Thread_a");

        //线程B
        Thread threadB = new Thread(()->{
            threadLocal.set("ThreadB：" + Thread.currentThread().getName());
            System.out.println("线程B本地变量中的值为：" + threadLocal.get());
            System.out.println("线程B没有删除本地变量：" + threadLocal.get());
        },"Thread_b");
        threadA.start();
        threadB.start();
    }
}
