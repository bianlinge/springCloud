package myrpc.service;

public class SeverDemo {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publish(helloService, 2020);
    }
}
