package com.dove.thread.callable.hasResult;

import java.util.concurrent.*;

/**
 * Future接口往往配合线程池来获取异步执行结果
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "测试Future获取异步结果";
            }
        });
        try {
            Object o = future.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
