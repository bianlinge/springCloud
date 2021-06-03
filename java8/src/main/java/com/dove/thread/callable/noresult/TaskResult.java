package com.dove.thread.callable.noresult;

import java.io.Serializable;

/**
 * 任务执行结果
 */
public class TaskResult implements Serializable {
    private static final long serialVersionUID = 8678277072402730062L;
    /**
     *   * 任务状态
     *   
     */

    private Integer taskStatus;
    /**
     *   * 任务消息
     *   
     */
    private String taskMessage;
    /**
     *   * 任务结果数据
     *   
     */

    private String taskResult;


    @Override
    public String toString() {
        return "TaskResult{" +
                "taskStatus=" + taskStatus +
                ", taskMessage='" + taskMessage + '\'' +
                ", taskResult='" + taskResult + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return TaskResult.serialVersionUID;
    }

    public Integer getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(final Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMessage() {
        return this.taskMessage;
    }

    public void setTaskMessage(final String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public String getTaskResult() {
        return this.taskResult;
    }

    public void setTaskResult(final String taskResult) {
        this.taskResult = taskResult;
    }
}
