package com.learn.testClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 分区操作练习：求前多少位的质数与和数
 *
 * @author suvue
 * @date 2020/4/25
 */
public class PartitioningByTest {
    public static void main(String[] args) {
        int number = 100;
        final Map<Boolean, List<Integer>> collect = IntStream.range(2, number)
                .boxed()
                .collect(Collectors.partitioningBy(PartitioningByTest::isPrime));
        System.out.println(collect.toString());

    }

    /**
     * 判断一个数是否是质数
     */
    private static boolean isPrime(int number) {
        final int sqrt = (int) Math.sqrt(number);
        return IntStream.rangeClosed(2, sqrt)
                .noneMatch(nr -> number % nr == 0);
    }
}
