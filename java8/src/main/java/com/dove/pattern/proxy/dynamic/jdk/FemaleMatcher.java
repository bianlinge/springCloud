package com.dove.pattern.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 动态代理
 * 1.被代理者必须实现某一接口
 * 2.代理者必须实现InvocationHandler 动态处理器接口
 */
public class FemaleMatcher implements InvocationHandler {
    private Chengxuyuan target;

    public Chengxuyuan getTarget() {
        return this.target;
    }

    public void setTarget( Chengxuyuan target) {
        this.target = target;
    }

    public FemaleMatcher(Chengxuyuan target) {
        this.target = target;
    }

    public Object getProxyInstance(Chengxuyuan target) {
        //this.target = target;
        Class<? extends Chengxuyuan> aClass = target.getClass();

/*      ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
        Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
        InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法*/
        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆 :我要给你找对象 已拿到你的要求");
        System.out.println("正在帮你找");
        method.invoke(target, args);
        return null;
    }
}
