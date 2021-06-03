package com.dove.web.stream.FunctionInterface;

import java.util.function.Supplier;

public class SupplierDemo {
    //2. 供给型接口 Supplier<T> :
    //
    //抽象方法： T get()：返回一个自定义数据  无参数 有返回值
    public static void main(String[] args) {
        String s = magicLamp(() -> "美女");
        String s1 = magicLamp(() -> {
            return "金钱";
        });
        System.out.println(s+"---"+s1);
    }
    public static String magicLamp(Supplier<String> wish){
        return wish.get();
    }
}
