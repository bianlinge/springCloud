package com.dove.lambda;

import com.dove.pattern.strategy.StrategyTest;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);

        // filter 只刻画出了 Stream ，但没有产生新的集合
        Stream<Integer> integerStream = integers.stream().filter(num -> {
            System.out.println(num);//不会打印出
            return num % 2 == 0;
        });

        long count = integers.stream().filter(num -> {
            System.out.println(num);
            return num % 2 == 0;
        }).count();
        System.out.println(count);


        Stream<String> stringStream = Stream.of("a", "b", "c");
        List<String> collect = stringStream.collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect1 = Stream.of("a", "b", "c")
                .map(a -> a.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(collect1);

        Set<String> collect2 = Stream.of("a", "1abc", "abc1")
                .filter(a -> a.startsWith("a"))//Predicate
                .map(a -> a.toUpperCase())//Function
                .collect(Collectors.toSet());//
        System.out.println(collect2);


        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4));
        List<Integer> collect4 = listStream
                .flatMap(numbers -> numbers.stream().map(i -> i + 1))
                .collect(Collectors.toList());
        System.out.println(collect4);

        //找出三人中年级最大的
        Track tom = new Track("Tom", 23);
        Track jerry = new Track("Jerry", 50);
        Track zhangsan = new Track("Zhangsan", 88);

        //max ->Optional
        Optional<Track> max = Stream.of(tom, jerry, zhangsan).max(Comparator.comparing(t -> t.getAge()));
        Track track = max.get();

        System.out.println(track);

        Track track2 = Stream.of(tom, jerry, zhangsan).min(Comparator.comparing(t -> t.getAge())).get();
        System.out.println(track2);

        //reduce 归纳 ->BinaryOperator
        Optional<Integer> reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce((integer, integer2) -> integer += integer2);
        Integer integer = reduce.get();
        System.out.println(integer);


        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        int c = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0, 1),
                        2),
                3);
        System.out.println(c);


        IntStream chars1 = "forEachmusicianCountVVincAndGet".chars();
        //计算一个字符串中小写字母的个数
        long count1 = chars1.filter(ch -> Character.isLowerCase(ch)).count();
        System.out.println(count1);

        String s1 = Stream.of("cianCountVVincAndGet", "sdfDfsdddFF", "forEachmusi")
                .min(Comparator.comparing(s -> s.chars().filter(ch -> Character.isLowerCase(ch)).count())).get();
        System.out.println(
                s1
        );


        List<HashMap<Integer, Track>> collect3 = Stream.of(tom, jerry, zhangsan).map(t -> {
            HashMap<Integer, Track> integerTrackHashMap = new HashMap<>();
            integerTrackHashMap.put(t.getAge(), t);
            return integerTrackHashMap;
        }).collect(Collectors.toList());
        System.out.println(collect3);

        //统计 summaryStatistics
        IntSummaryStatistics intSummaryStatistics = Stream.of(tom, jerry, zhangsan)
                .mapToInt(t -> t.getAge()).summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                intSummaryStatistics.getMax(),
                intSummaryStatistics.getMin(),
                intSummaryStatistics.getAverage(),
                intSummaryStatistics.getSum());

    }

    static class Track {
        String name;
        Integer age;

        @Override
        public String toString() {
            return "Track{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Track(final String name, final Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public Integer getAge() {
            return this.age;
        }

        public void setAge(final Integer age) {
            this.age = age;
        }
    }
}
