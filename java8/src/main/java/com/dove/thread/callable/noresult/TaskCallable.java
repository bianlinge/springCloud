package com.dove.thread.callable.noresult;

/**
 *  定义回调接口
 * @param <T>
 */
public interface TaskCallable<T> {
    T callable(T t);
}
