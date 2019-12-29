package com.imodule.duoxiancheng.Future_Test;

import java.util.concurrent.*;

public class FutureTask_ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        FutureTask<String> futureTask1 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "凉菜准备好了";
            }
        });
        executor.submit(futureTask1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(futureTask1.get());
        FutureTask<String> futureTask2 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "包子准备好了";
            }
        });
        executor.submit(futureTask2);
        System.out.println(futureTask2.get());


        executor.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));

    }
}
