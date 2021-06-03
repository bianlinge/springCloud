package com.dove.lambda;

public class ParentImpl implements Parent {
    @Override
    public void message(String body) {
        System.out.println(body);
    }
    @Override
    public String getLastMessage() {
        return "";
    }
}
