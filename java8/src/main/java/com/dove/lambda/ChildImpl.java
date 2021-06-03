package com.dove.lambda;

public class ChildImpl implements Child {
    @Override
    public void message(String body) {
        System.out.println(body);
    }

 /*   @Override
    public void welcome() {
        message("childimpl hello");
    }*/

    @Override
    public String getLastMessage() {
        return null;
    }
}
