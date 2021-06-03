package com.dove.fanxing;

public class Generic<T> {
    private T key;

    public T getKey() {
        return this.key;
    }

    //泛型类中的使用了泛型的成员方法并不是泛型方法
    public void setKey(T key) {
        this.key = key;
    }

    public Generic(T key) {
        this.key = key;
    }

    //1. public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
    //2. <T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T
    //3. 这个T可以出现在这个泛型方法的任意位置
    public  <T> T genericMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t = tClass.newInstance();
        return t;
    }
}
