package com.dove.lambda;

public interface Child extends Parent {
    @Override
    default void welcome() {
        message("Child: Hi!");
    }
}
