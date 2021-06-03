package com.dove.lambda;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

//        函数式接口;有且只有一个方法抽象方法的接口,可以有其他的方法.
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Integer> predicate = number -> number > 12;
        boolean test = predicate.test(32);
        System.out.println(test);


        BinaryOperator<Long> addLongs = (x, y) -> x + y;
//        BinaryOperator add = (x, y) -> x + y; <Long>信息不全无法推断


        ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(SimpleDateFormat::new);
        SimpleDateFormat simpleDateFormat = local.get();

    }
}
