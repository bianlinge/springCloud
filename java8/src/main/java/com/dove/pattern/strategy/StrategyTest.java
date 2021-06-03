package com.dove.pattern.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        Order order = new Order("110", "20201031001", 1200.22);
//        PayState pay = order.pay(PayType.ALI_PAY);
        PayState pay = order.pay(PayType.JD_APY);
        System.out.println(pay);
    }
}
