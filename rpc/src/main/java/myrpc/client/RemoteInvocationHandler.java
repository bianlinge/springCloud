package myrpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//处理请求的处理器
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    //
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        TCPTrasport tcpTrasport = new TCPTrasport(host,port);
        Object returnObject = tcpTrasport.send(rpcRequest);
        return returnObject;
    }
}
