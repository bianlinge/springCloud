package com.imodule.stream.FunctionInterface;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        //1. 消费型接口 Consumer<T> :
        //抽象方法：void accept(T t)：  有参无返回值
        // 接收一个参数进行消费，但无需返回结果
        Goods goods = new Goods("SK2", 1000.0);
        spentMoney(goods, goods12 -> System.out.println("spendMoney："+ goods12.getCost()));

        spentMoneyAndLog(goods,goods1 -> System.out.println(goods.getGoodName()));
    }
    //任性地花
    public static void spentMoney(Goods goods,Consumer<Goods> consumer) {
        consumer.accept(goods);
    }
    //花一笔记一笔
    public static void spentMoneyAndLog(Goods goods, Consumer<Goods> consumer) {
        Consumer<Goods> logConsumer = (g) -> System.out.println("买" + g.getGoodName() + "用了" + g.getCost() + "元！");
        consumer.andThen(logConsumer).accept(goods);
    }


}
