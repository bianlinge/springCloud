package com.dove.pattern.strategy;

public class WeChatPay implements Payment {
    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("欢迎使用微信支付");
        System.out.println("正常支付....");
        return new PayState(200,"支付成功","支付用户: "+uid+",支付金额: "+amount);
    }
}
