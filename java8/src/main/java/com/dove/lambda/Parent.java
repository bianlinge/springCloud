package com.dove.lambda;

public interface Parent {
    public void message(String body);
    public default void welcome() {
        message("Parent: Hi!");
    }
    public String getLastMessage();
}
