package com.dove.pattern.strategy;

public enum PayType {
    ALI_PAY(new AliPay()),
    JD_APY(new JDPay()),
    WECHAT_PAY(new WeChatPay()),
    UNION_PAY(new UnionPay());

    private Payment payment;

    PayType(Payment payment) {
        this.payment = payment;
    }

    //一般会私有化枚举成员变量 对外提供公共获取成员变量的方法
    public Payment getPayment() {
        return this.payment;
    }

    public static void main(String[] args) {
        PayType ali_pay = PayType.valueOf("ALI_PAY");
        Payment payment = ali_pay.getPayment();
        System.out.println(payment==ali_pay.payment);
    }

}
