package com.dove.thread.scheduleExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        //initialDelay 第一次执行的延迟的时间
        //固定频率执行
        // scheduledExecutorService.scheduleAtFixedRate((Runnable) () -> System.out.println("测试ScheduledExecutorService"), 2, 1, TimeUnit.SECONDS);

        //相对固定的延迟
        scheduledExecutorService.scheduleWithFixedDelay((Runnable) () -> System.out.println("测试ScheduledExecutorService"), 1, 1, TimeUnit.SECONDS);
        //主线程睡10s
        Thread.sleep(10000);
        System.out.println("正在关闭线程池");
        scheduledExecutorService.shutdown();

        boolean isClosed;
        do {
            isClosed = scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("正在等待线程池中的任务执行完毕");
        } while (!isClosed);
        System.out.println("所有线程执行结束 线程池关闭");
    }
}
