package myrpc.service;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String msg) {
        return "Hello ," + msg;
    }
}
