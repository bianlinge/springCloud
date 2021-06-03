package com.dove.web.stream;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Stream_Test {
    public static void main(String[] args) throws FileNotFoundException {
 /*       //1. 流的常用创建方法
        //
        //1.1 使用Collection下的 stream() 和 parallelStream() 方法

        List<String> list = new ArrayList();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        list.add("zhaoliu");

        Stream<String> stream = list.stream();//获取一个顺序流
        stream.forEach(s-> System.out.println(s));

        Stream<String> stream1 = list.parallelStream();
        stream1.forEach(s-> System.out.println(s));//获取一个并行流 无序

        System.out.println("-------------------------------------");
       // 1.2 使用Arrays 中的 stream() 方法，将数组转成流
        Integer[] nums = new Integer[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i]=i+1;
        }

        Stream<Integer> arrayStream = Arrays.stream(nums);
        arrayStream.forEach(num-> System.out.println(num));
        System.out.println("------------------------------------");
        //1.3 使用Stream中的静态方法：of()、iterate()、generate()
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> s2 = Stream.iterate(0, x -> x + 2).limit(8);
        s2.forEach(System.out::println);

        System.out.println("----------------------------");
        //1.4 使用 BufferedReader.lines() 方法，将每行内容转成流
        BufferedReader bf = new BufferedReader(new FileReader("F:/图片/test.txt"));
        Stream<String> lines = bf.lines();
        lines.forEach(System.out::println);

        //1.5 使用 Pattern.splitAsStream() 方法，将字符串分隔成流

       Pattern pattern = Pattern.compile(",");
        Stream<String> splitAsStream = pattern.splitAsStream("a,b,c,d,e");
        splitAsStream.forEach(System.out::println);
        System.out.println("-----------------------------------");
        //2. 流的中间操作
        //
        //2.1 筛选与切片
        //        filter：过滤流中的某些元素
        //        limit(n)：获取n个元素
        //        skip(n)：跳过n元素，配合limit(n)可实现分页
        //        distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
        Stream<Integer> integerStream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> newStream = integerStream.filter((s) -> s > 5)//6,6,7,9,8,10,12,14,14
                .distinct()//去重，6，7，9，8，10，12，14
                .skip(2)//9,8,10,14
                .limit(3);//9,8,10
        newStream.forEach(System.out::println);
        System.out.println("-------------------------------");
      *//*  2.2 映射
        map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。*//*
        List<String> list1 = Arrays.asList("a,b,c", "1,2,3");
        Stream<String> mapStream = list1.stream().map(s -> s.replace(",", ""));
        mapStream.forEach(System.out::println);
        System.out.println("-------------------------------");

        Stream<String> flatmapStream = list1.stream().flatMap((String s) -> {
            String[] split = s.split(",");
            Stream<String> stringStream = Arrays.stream(split);
            return stringStream;
        });
        flatmapStream.forEach(System.out::println);
        System.out.println("-------------------------------");
        //2.3 排序
        //        sorted()：自然排序，流中元素需实现Comparable接口
        //        sorted(Comparator com)：定制排序，自定义Comparator排序器

        List<String> lists = Arrays.asList("aa", "ff", "dd");
        lists.stream().sorted().forEach(System.out::println);
        System.out.println("-------------------------------");


        Student stu1 = new Student("aa", 10);
        Student stu2 = new Student("bb", 20);
        Student stu3 = new Student("aa", 30);
        Student stu4 = new Student("dd", 40);

        List<Student> students = Arrays.asList(stu1, stu2, stu3, stu4);
        students.stream().sorted((o1, o2) -> {
            if(o1.getName().equals(o2.getName())){
                return o1.getAge()-o2.getAge();
            }else {
                return o1.getName().compareTo(o2.getName());
            }
        }).forEach(System.out::println);
        System.out.println("--------------------");
        //2.4 消费
        //peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。

        Student sd1 = new Student("aa", 10);
        Student sd2 = new Student("bb", 20);
        List<Student> studentList = Arrays.asList(sd1, sd2);

        studentList.stream()
                .peek(o -> o.setAge(100))
                .forEach(System.out::println);
        //3. 流的终止操作
        //
        //3.1 匹配、聚合操作
        //        allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
        //        noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
        //        anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
        //        findFirst：返回流中第一个元素
        //        findAny：返回流中的任意元素
        //        count：返回流中元素的总个数
        //        max：返回流中元素最大值
        //        min：返回流中元素最小值
        System.out.println("-------------------------------------");*/
        List<Integer> integers = Arrays.asList(10, 21, 5, 6, 8, 13);
        boolean b = integers.stream().allMatch(num -> num >= 5);
        System.out.println(b);
        boolean b1 = integers.stream().noneMatch(num -> num > 100);
        System.out.println(b1);
        boolean b2 = integers.stream().anyMatch(num -> num == 21);
        System.out.println(b2);

        Optional<Integer> first = integers.stream().findFirst();
        Integer integer = first.get();
        System.out.println(integer);
        Integer integer1 = integers.stream().findAny().get();
        System.out.println(integer1);

        Integer in = null;
        //Optional<Integer> optional = Optional.of(in);
        // of（T）不能存放NUll值抛出异常 NullPointerException
        Optional<Integer> optional1 = Optional.ofNullable(in);
        System.out.println(optional1.isPresent() ? optional1.get() : "不存在");

        System.out.println(integers.stream().count());
        System.out.println(integers.stream().max(Integer::compareTo).get());
        Optional<Integer> min = integers.stream().min(Integer::compareTo);
        if (min.isPresent()) {
            System.out.println(min.get());
        }
        System.out.println("-------------------------------------");
        /*3.3 收集操作
        collect：接收一个Collector实例，将流中元素收集成另外一个数据结构。
        Collector<T, A, R> 是一个接口，有以下5个抽象方法：
            Supplier<A> supplier()：创建一个结果容器A
            BiConsumer<A, T> accumulator()：消费型接口，第一个参数为容器A，第二个参数为流中元素T。
            BinaryOperator<A> combiner()：函数接口，该参数的作用跟上一个方法(reduce)中的combiner参数一样，将并行流中各                                                                 个子进程的运行结果(accumulator函数操作后的容器A)进行合并。
            Function<A, R> finisher()：函数式接口，参数为：容器A，返回类型为：collect方法最终想要的结果R。
            Set<Characteristics> characteristics()：返回一个不可变的Set集合，用来表明该Collector的特征。有以下三个特征：
                CONCURRENT：表示此收集器支持并发。（官方文档还有其他描述，暂时没去探索，故不作过多翻译）
                UNORDERED：表示该收集操作不会保留流中元素原有的顺序。
                IDENTITY_FINISH：表示finisher参数只是标识而已，可忽略。*/


        Person p1 = new Person("aa", 10, 1);
        Person p2 = new Person("bb", 20, 2);
        Person p3 = new Person("cc", 10, 3);
        List<Person> list = Arrays.asList(p1, p2, p3);
        List<Integer> ageList = list.stream().map(p -> p.getAge()).collect(Collectors.toList());
        System.out.println(ageList);
        Set<Integer> orderSet = list.stream().map(p -> p.getOrder()).collect(Collectors.toSet());
        System.out.println(orderSet);
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Person::getName, Person::getOrder));
        Map<String, Integer> map1 = list.stream().collect(Collectors.toMap(person -> person.getName(), person -> person.getOrder()));
        System.out.println(map);

        //字符串分隔符连接

        String collect = list.stream().map(ps -> ps.getName()).collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect);

        ResourceLoader resourceLoader = new DefaultResourceLoader();

    }
}
