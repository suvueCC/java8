package com.learn.testClass;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;

/**
 * 函数式接口
 *
 * @author suvue
 * @date 2020/4/24
 */
public class FunctionInfceTest {
    public static void main(String[] args) {
        System.out.println(checkNumber(20, (int i) -> i % 2 == 0));
        System.out.println(checkNumber(21, (int i) -> i % 2 == 0));

        Function<String, Integer> function = (String s) -> s.length();
        System.out.println(function.apply("aaaaa"));

        Consumer<String> consumer = (String s) -> {
            System.out.println(Arrays.asList(s).size());
        };
        consumer.accept("bbbbb");

        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
        System.out.println(comparator.compare(1, 2));
    }

    private static boolean checkNumber(int i, IntPredicate intPredicate) {
        return intPredicate.test(i);
    }

}
