package com.dove.thread.callable.noresult;

/**
 * 任务的执行类是具体执行任务的类，实现Runnable接口，
 * 在此类中定义一个回调接口类型的成员变量和一个String类型的任务参数
 * （模拟任务的参数），并在构造方法中注入回调接口和任务参数。
 */
public class TaskExecutor implements Runnable {
    private TaskCallable<TaskResult> taskCallable;
    private String taskParameter;

    public TaskExecutor(final TaskCallable<TaskResult> taskCallable, final String taskParameter) {
        this.taskCallable = taskCallable;
        this.taskParameter = taskParameter;
    }

    @Override
    public void run() {
        //执行业务逻辑 将结果数据封装成TaskResult对象并返回
        //.......
        TaskResult result = new TaskResult();
        result.setTaskStatus(1);
        result.setTaskMessage(this.taskParameter);
        result.setTaskResult("异步回调成功");
        taskCallable.callable(result);
    }
}
