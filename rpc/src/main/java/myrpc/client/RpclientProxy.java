package myrpc.client;

import java.lang.reflect.Proxy;

//客户端 代理类
public class RpclientProxy {
    public <T> T clientProxy(Class<T> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls},
                new RemoteInvocationHandler(host, port));
    }
}
