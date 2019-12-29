package com.imodule.stream.FunctionInterface;

import java.util.function.Function;

public class FunctionDemo {
    /*抽象方法：  有参数 有返回值

        R apply(T t)：传入一个参数，返回想要的结果。

默认方法：

       为了下面能更好地解释，这里先假设有两个Function实例：f1,f2

       default <V> Function<V, R> compose(Function<? super V, ? extends T> before)：f1.compose(f2).apply(arg)，表示先执行f2，然后将得到的结果传给f1执行。

       default <V> Function<T,V> andThen(Function<? super R,? extends V> after)：f1.andThen(f2).apply(arg)，表示先执行f1，然后将得到的结果传给f2执行。

静态方法：

       static <T> Function<T, T> identity()：获取到一个输入参数和返回结果一样的Function实例
！*/
    public static void main(String[] args) {

        //第一次，妈妈给小明10元去买酱油 酱油10块钱 小明没有小费
        double tips = firstBuy(10, m -> {
            return 10 - 10.0;
        });
        System.out.println("小明得到的小费：" + tips);
        //第二次，妈妈还是给小明10元买酱油，小明思考了一下，拒绝了
        System.out.println("小明将妈妈给的" + Function.identity().apply(10) + "元还了回去");
        //妈妈在了解完情况后，给了小明20元去买，小明当然很愉快去了
        double tips2 = secondBuy(20, m -> {
            System.out.println("买酱油前有" + m + "元");
            double v2 = m-10.0;
            System.out.println("买完酱油后剩下" + v2 + "元");
            return v2;
        });
        System.out.println("小明剩下的小费：" + tips2);

    }

    public static double firstBuy(double money, Function<Double, Double> buy) {
        return buy.apply(money);
    }

    public static double secondBuy(double money, Function<Double, Double> buy) {

        //在去的路上小明先买了冰淇淋
        Function<Double, Double> beforeBuy = (m) -> {
            System.out.println("第一次买冰淇淋前有" + m + "元");
            double v1 = m - 2.0;
            System.out.println("买完冰淇淋后剩下" + v1 + "元");
            return v1;
        };
        //回来的路上小明又买了冰淇淋
        Function<Double, Double> afterBuy = (m) -> {
            System.out.println("第二次买冰淇淋前有" + m + "元");
            double v3 = m - 2.0;
            System.out.println("买完冰淇淋后剩下" + v3 + "元");
            return v3;
        };
        //先执行beforeBuy给buy 然后 执行buy给afterBuy
        return buy.compose(beforeBuy).andThen(afterBuy).apply(money);
    }
}
