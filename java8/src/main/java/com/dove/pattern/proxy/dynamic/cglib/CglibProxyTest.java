package com.dove.pattern.proxy.dynamic.cglib;

public class CglibProxyTest {
    public static void main(String[] args) {

        ChengXuYuan chengXuYuan = new ChengXuYuan();

        MeiPoProxy meiPoProxy = new MeiPoProxy();
        ChengXuYuan proxyInstace = (ChengXuYuan) meiPoProxy.getProxyInstace(chengXuYuan);

        proxyInstace.findGF();
    }
}
