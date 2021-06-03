package com.dove.thread.callable.hasResult;

import java.util.concurrent.*;

/**
 * FutureTask
 * FutureTask类既可以结合Thread类使用也可以结合线程池使用
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "测试FutureTask获取异步结果";
            }
        });

        new Thread(futureTask).start();
        Object res = futureTask.get();
        System.out.println(res);


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(futureTask);
//        executorService.submit(futureTask);
        Object o = futureTask.get();
        System.out.println(o);

        executorService.shutdown();

    }
}
