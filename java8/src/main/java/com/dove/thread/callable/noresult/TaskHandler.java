package com.dove.thread.callable.noresult;

/**
 * 回调实现类
 */
public class TaskHandler implements TaskCallable<TaskResult> {
    @Override
    public TaskResult callable(TaskResult taskResult) {
        System.out.println(taskResult.toString());
        return taskResult;
    }
}
