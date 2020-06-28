package com.learn.testClass;

import java.util.stream.Stream;

/**
 * iterare是迭代类型的无限流
 *
 * @author suvue
 * @date 2020/4/25
 */
public class IterateTest {
    public static void main(String[] args) {
        //裴波纳契数 因为是无限流，所以这里做个限制为20组
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println(t[0] + " , " + t[1]));

        //如果你想打印出一排裴波纳契数，可以采用map映射
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}
