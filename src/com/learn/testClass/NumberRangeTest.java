package com.learn.testClass;

import java.util.stream.IntStream;
/**
 * 数值范围
 *
 * @author suvue
 * @date 2020/4/24
 */
public class NumberRangeTest {
    public static void main(String[] args){
        //获取数值范围 range方法不包含结尾
        final long count1 = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .count();//50
        System.out.println(count1);
        final long count2 = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0)
                .count();//49
        System.out.println(count2);
    }
}
