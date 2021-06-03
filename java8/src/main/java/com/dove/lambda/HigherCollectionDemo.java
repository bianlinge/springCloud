package com.dove.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HigherCollectionDemo {
    public static void main(String[] args) {
        //找出三人中年级最大的
        Track tom = new Track("Tom", 23);
        Track jerry = new Track("Jerry", 50);
        Track zhangsan = new Track("Zhangsan", 88);

        //方法的引用 track -> track.getName() == Track::getName
        Set<String> collect1 = Stream.of(tom, jerry, zhangsan).map(track -> track.getName()).collect(Collectors.toSet());
        Set<String> collect2 = Stream.of(tom, jerry, zhangsan).map(Track::getName).collect(Collectors.toSet());

        System.out.println(collect2);

        BiFunction<String, Integer, Track> aNew = Track::new;
        Track dove = aNew.apply("dove", 100);
        System.out.println(dove);


        List<Integer> numbers = Arrays.asList(3, 4, 1, 2);
        System.out.println(numbers);
        List<Integer> sameOrder = numbers.stream()
                .collect(Collectors.toList());
        System.out.println(sameOrder);

        Set<Integer> strings = new HashSet<>();

        strings.add(3);
        strings.add(41);
        strings.add(2);
        strings.add(1);
        Iterator<Integer> iterator = strings.iterator();
        System.out.println(iterator.next());
        List<Integer> collect = strings.stream().map(x -> x + 1).sorted().collect(Collectors.toList());
        System.out.println(collect);
        Optional<Integer> collect7 = strings.stream().map(x -> x + 1).sorted().collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("Collectors.maxBy" + collect7.get());

        //sorted()
        ArrayList<String> list = new ArrayList<>();
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("b");
        list.add("b");
        System.out.println(list);
        List<String> collect3 = list.stream().sorted().collect(Collectors.toList());
        System.out.println(collect3);
        //distinct
        List<String> collect4 = list.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println(collect4);

        //list to treemap
        TreeSet<String> collect5 = list.stream().sorted().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect5);

        List<String> list1 = new ArrayList<>();
        list1.add("dsdf");
        list1.add("esdf");
        list1.add("faadf");
        list1.add("bfadfasfaasfa");
        list1.add("bafafasd");

        Stream<String> stream = list1.stream();
        Function<String, Long> getCount = str -> Long.valueOf(str.length());
        List<Long> collect6 = stream.map(getCount).collect(Collectors.toList());
        System.out.println(collect6);

        String s = list1.stream().max(Comparator.comparing(String::length)).get();
        System.out.println(s);


        OptionalDouble average = list1.stream().mapToLong(s1 -> s1.length()).average();
        double asDouble = average.getAsDouble();
        System.out.println(asDouble);
        IntSummaryStatistics longSummaryStatistics = list1.stream().mapToInt(s1 -> s1.length())
                .peek(nation -> System.out.println("Found nationality: " + nation))
                .summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                longSummaryStatistics.getMax(),
                longSummaryStatistics.getMin(),
                longSummaryStatistics.getAverage(),
                longSummaryStatistics.getSum());

        System.out.println();


        // 创建一个包含人名称的流（英文名和中文名）
        Stream<String> streams = Stream.of("Alen", "Hebe", "Zebe", "张成瑶", "钟其林");
        // 通过判断人名称的首字母是否为英文字母，将其分为两个不同流
        final Map<Boolean, List<String>> map = streams.collect(Collectors.partitioningBy(ss -> {
            // 如果是英文字母，则将其划分到英文人名，否则划分到中文人名
            int code = ss.codePointAt(0);
            return (code >= 65 && code <= 90) || (code >= 97 && code <= 122);
        }));
// 输出分组结果
        map.forEach((isEnglishName, names) -> {
            if (isEnglishName) {
                System.out.println("英文名称如下：");
            } else {
                System.out.println("中文名称如下：");
            }
            names.forEach(name -> System.out.println("\t" + name));
        });


        Map<Boolean, List<Integer>> collect8 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println(collect8);


        Map<String, List<Integer>> collect9 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).collect(Collectors.groupingBy(integer -> {
            if (integer / 10 > 0) {
                return "2两位";
            } else {
                return "1两位";
            }

        }));

        System.out.println(collect9);


        String collect10 = Stream.of(tom, jerry, zhangsan)
                .map(Track::getName)
                .collect(Collectors.joining("][", "[", "]"));
        System.out.println(collect10);


        StringCombiner combined =
                Stream.of(tom, jerry, zhangsan)
                        .map(Track::getName)
                        .reduce(new StringCombiner(", ", "[", "]"),
                                StringCombiner::add,
                                StringCombiner::merge);
        String result = combined.toString();
        System.out.println(result);


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        Integer conut = integers.stream().reduce(1, (integer, integer2) -> integer + integer2).intValue();
        int conut2 = integers.parallelStream().reduce(0,
              /*  (integer, integer2) -> {
                    System.out.println("integer: " + integer);
                    System.out.println("integer2: " + integer2);
                    System.out.println("BiFunction acc: " + (integer + integer2));
                    return (integer + integer2);
                },*/
                (integer, integer2) -> {
                    System.out.println("integer: " + integer);
                    System.out.println("integer2: " + integer2);
                    System.out.println("BinaryOperator acc: " + (integer + integer2));
                    return (integer + integer2);
                }).intValue();
        System.out.println(conut);
        System.out.println(conut2);


    }
}
