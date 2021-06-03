package com.dove.pattern.proxy.staticproxy;

/**
 * 静态代理
 * 代理者提被代理者完成所有方法
 * 但是必须知道被代理者的所有行为 已知行为
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Person person = new Person();
        Meipo meipo = new Meipo(person);
        meipo.findGF();
    }
}
