package com.dove.multithread.countdownlatch;

import java.util.concurrent.Semaphore;

//限制访问请求量
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            new Doanthing(i,semaphore).start();
        }
    }
    static class Doanthing extends Thread{
        private int num;
        private Semaphore semaphore;

        public Doanthing(final int num, final Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
               semaphore.acquire();//获取一个令牌
                System.out.println("第"+num+"个线程进入");
                Thread.sleep(2000);
                semaphore.release();//释放令牌
                System.out.println("第"+num+"个线程释放");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
