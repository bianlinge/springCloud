package com.dove.pattern.single;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例适用于创建实例非常多的情况，便于管理。
 * 但是HashMap是非线程安全的
 * 可以使用ConcurrentHashMap 保证线程安全
 */
public class ContainerSingleton {
    private ContainerSingleton() {
    }

//    private static Map<String, Object> ioc = new HashMap<>();

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();


    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object object = null;
                try {
                    object = Class.forName(className);
                    ioc.put(className, object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return object;
            } else {
                return ioc.get(className);
            }
        }

    }
}
