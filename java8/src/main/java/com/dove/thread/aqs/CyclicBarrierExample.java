package com.dove.thread.aqs;

import jdk.nashorn.internal.ir.CallNode;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample {
    private static final int threadCount = 10;
    static final ExecutorService executorService = Executors.newCachedThreadPool();

//    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        log.info("callback is running");
});

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    test(finalI);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(100);
        log.info("ready..."+threadNum);
        cyclicBarrier.await();
        log.info("conitue..."+threadNum);
    }
}
