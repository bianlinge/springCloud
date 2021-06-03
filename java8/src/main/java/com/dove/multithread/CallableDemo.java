package com.dove.multithread;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);

        //中间执行其他业务逻辑  最终在future.get()中阻塞

        String s = future.get();//阻塞
        System.out.println(s);
        executorService.shutdown();

    }

    @Override
    public String call() throws Exception {
        return "String" + 1;
    }
}
