package com.dove.pattern.strategy;


public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public PayState pay(PayType payType) {
        return payType.getPayment().pay(uid,amount);
    }
    public Order(final String uid, final String orderId, final double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
