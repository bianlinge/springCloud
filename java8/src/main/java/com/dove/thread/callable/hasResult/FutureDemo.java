package com.dove.thread.callable.hasResult;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask futureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "futuretask test";
            }
        });

        try {
            executorService.submit(futureTask);
            //execute只能提交Runnable类型的任务，而submit既能提交Runnable类型任务也能提交Callable类型任务。
//            boolean cancel = futureTask.cancel(true);
//            //取消任务 ： 已经结束或者因 其他原因不能取消时，方法会返回false
//            System.out.println(cancel);

            //判断任务在完成之前是否被取消，如果在任务完成之前被取消，则返回true；否则，返回false
            boolean cancelled = futureTask.isCancelled();
            System.out.println(cancelled);


            boolean done1 = futureTask.isDone();
            System.out.println("任务是否已经完成"+done1);


            System.out.println(futureTask.get());

            //任务执行完成指的是 返回值已经处理futureTask.get()
            boolean done = futureTask.isDone();
            System.out.println("任务是否已经完成"+done);
        } finally {
            executorService.shutdown();
        }

    }
}
