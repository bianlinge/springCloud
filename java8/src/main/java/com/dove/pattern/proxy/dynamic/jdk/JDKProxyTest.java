package com.dove.pattern.proxy.dynamic.jdk;


public class JDKProxyTest {
    public static void main(String[] args) {
        Chengxuyuan chengxuyuan = new Chengxuyuan();
        FemaleMatcher femaleMatcher = new FemaleMatcher(chengxuyuan);
        MaNong proxyInstance = (MaNong) femaleMatcher.getProxyInstance(chengxuyuan);
        proxyInstance.findGF();

/////字节码重组
//        代理类必须持有被代理对象的引用 并获取被代理对象所有的接口--反射获取
        //JDK Proxy 类重写生成新的类 final 修饰的 $Proxy0  同时新的类要实现被代理类所有的接口
        //动态生成java代码 增加新的业务逻辑增强
        //编译生成java代码.class
        //再重新加载到JVM中间运行
//$

    }
}
