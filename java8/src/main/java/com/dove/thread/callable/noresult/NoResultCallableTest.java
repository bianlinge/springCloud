package com.dove.thread.callable.noresult;

public class NoResultCallableTest {
    public static void main(String[] args) {
        TaskHandler taskHandler = new TaskHandler();
        TaskExecutor runnable = new TaskExecutor(taskHandler, "测试回调任务");
        new Thread(runnable).start();
    }
}
