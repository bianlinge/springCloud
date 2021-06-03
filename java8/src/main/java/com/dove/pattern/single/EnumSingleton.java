package com.dove.pattern.single;

/**
 * 枚举单例 线程安全
 * 枚举式单例可避免序列化破坏和反射破坏
 */
public enum  EnumSingleton {
    INSTANCE;
    private  Object data;

    public Object getData() {
        return this.data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
