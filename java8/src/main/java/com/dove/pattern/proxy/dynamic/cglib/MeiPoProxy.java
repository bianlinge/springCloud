package com.dove.pattern.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib 代理类 实现MethodInterceptor
 */
public class MeiPoProxy implements MethodInterceptor {
    private ChengXuYuan target;

    public Object getProxyInstace(final ChengXuYuan target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是媒婆 :我要给你找对象 已拿到你的要求");
        System.out.println("正在帮你找");
        Object invoke = methodProxy.invokeSuper(o, objects);
//        Object invoke = methodProxy.invoke(o, objects);//死循环

        return invoke;
    }
}
