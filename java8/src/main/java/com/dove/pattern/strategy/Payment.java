package com.dove.pattern.strategy;

public interface Payment {
    public PayState pay(String uid,double amount);
}
