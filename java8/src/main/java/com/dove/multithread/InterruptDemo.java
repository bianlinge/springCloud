package com.dove.multithread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    private static int i;
    public static void main(String[] args) {
      Thread thread =  new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                i++;
            }
          System.out.println(i);
        },"interruptDemo");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            thread.interrupt(); //终止线程 设置interrupt为true

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
