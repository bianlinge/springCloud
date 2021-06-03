package com.dove.lambda;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("string");
        String s = optional.get();
        System.out.println(s);
        int length = "".length();
        System.out.println(length);

        Optional<Object> empty = Optional.empty();
        System.out.println(empty.isPresent());

        Optional<Object> o1 = Optional.ofNullable(null);
        System.out.println(o1.isPresent());
        Object o = o1.orElseGet(() -> "no element");
        System.out.println(o);
        Object no_string = o1.orElse("no string");
        System.out.println(no_string);

    }
}
