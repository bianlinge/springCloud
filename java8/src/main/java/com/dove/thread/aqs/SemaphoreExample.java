package com.dove.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.security.Signature;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample {
    private static final int threadCount = 200;

    static final ExecutorService executorService = Executors.newCachedThreadPool();
    //控制同一时间并发线程的数目。能够完成对于信号量的控制，可以控制某个资源可被同时访问的个数
    //最大为同时10个线程访问
    static final Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    //同一时间只能允许3个线程同时访问
                    semaphore.acquire(3);//获取多个个许可  semaphore.acquire();//获取一个许可
                    test(finalI);
                    Thread.sleep(new Random().nextInt(100)); // 模拟随机执行时长
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3-semaphore.availablePermits()) + "个并发");
                    semaphore.release(3);//释放多个个许可 semaphore.release();//释放一个许可

                    //尝试获取一个许可，没有获取当前的线程则会被丢弃
                    //假设有这样一个场景，并发太高了，即使使用Semaphore进行控制，处理起来也比较棘手。假设系统当前允许的最高并发数是3，
                    //超过3后就需要丢弃，使用Semaphore也能实现这样的场景
                   /* if (semaphore.tryAcquire()) {
                        test(finalI);
                        semaphore.release();
                    }*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            });
        }

        executorService.shutdown();
        log.info("finish");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info (threadNum+">>>i");
    }
}
