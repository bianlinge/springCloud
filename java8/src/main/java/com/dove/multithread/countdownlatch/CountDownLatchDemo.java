package com.dove.multithread.countdownlatch;


import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    //计数器
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()->{
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            countDownLatch.countDown();
        }).start();

        new Thread(()->{
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
        System.out.println("执行完毕");
    }

}
