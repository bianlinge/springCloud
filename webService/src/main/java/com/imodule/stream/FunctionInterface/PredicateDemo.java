package com.imodule.stream.FunctionInterface;

import java.util.function.Predicate;

public class PredicateDemo {
    /*4. 断言型接口 Predicate<T> :

抽象方法：

        boolean test(T t)：传入一个参数，返回一个布尔值

默认方法：

       为了下面能更好地解释，这里先假设有两个Predicate实例：p1,p2

       default Predicate<T> negate()：表示 ! p1.test()

       default Predicate<T> and(Predicate<? super T> other)：p1.and(p2).test(arg)，表示p1.test(arg) && p2.test(arg)

       default Predicate<T> or(Predicate<? super T> other)：p1.or(f2).test(arg)，表示p1.test(arg) || p2.test(arg)

静态方法：

       static <T> Predicate<T> isEqual(Object targetRef)：获取到一个Predicate实例p，p.test(arg) 表示targetRef 是否等于arg
*/
    public static void main(String[] args) {
        Predicate<String> predicate1 = s -> s.equals("nice");
        Predicate<String> predicate2 = s -> s.startsWith("n");

        boolean result1 = predicate1.test("nice");
        System.out.println(result1);
        boolean result2 = predicate2.test("nice");
        System.out.println(result2);
        boolean result3 = predicate1.and(predicate2).test("nice");
        System.out.println(result3);
        boolean result4 = predicate1.and(predicate2).test("good");
        System.out.println(result4);

        Predicate<String> p = Predicate.isEqual("当这个参数为null，使用==判断，否则使用equal方法判断");
        boolean result5 = p.test("end");
        System.out.println(result5);

    }
}
