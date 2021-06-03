package com.dove.thread;

public class ThreadOrder {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("thread01"));
        Thread thread2 = new Thread(() -> System.out.println("thread02"));
        Thread thread3 = new Thread(() -> System.out.println("thread03"));
        thread1.start();
        thread1.join();//阻塞 等待线程1 执行完在走下主线程
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
