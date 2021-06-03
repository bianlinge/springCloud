package myrpc.client;

public class RpcClientDemo {
    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 2020;
        RpclientProxy rpclientProxy = new RpclientProxy();
        HelloService  helloService = rpclientProxy.clientProxy(HelloService.class, host, port);
        String tom = helloService.sayHello("tom");

        System.out.println(tom);
    }
}
