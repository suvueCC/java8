package com.learn.testClass;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * reduce的测试类
 *
 * @author suvue
 * @date 2020/4/24
 */
public class ReduceTest {
    public static void main(String[] args){
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //求和
        final Integer sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        //求乘积
        final Integer multi = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(multi);
        //最大值
        final Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max.get());
        //最小值
        final Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(min.get());
    }
}
