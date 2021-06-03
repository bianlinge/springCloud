package com.dove.dubbo;

public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sasyHello(String msg) {
        return "Hello" + msg;
    }
}
