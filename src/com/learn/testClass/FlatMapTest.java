package com.learn.testClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * flatmap的测试类
 *
 * @author suvue
 * @date 2020/4/24
 */
public class FlatMapTest {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        //flatmap能把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        final List<String> collect = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect.toString());

        //flatmap在嵌套循环中的使用
        final List<Integer> list1 = Arrays.asList(1, 2, 3);
        final List<Integer> list2 = Arrays.asList(3, 4);
        final List<int[]> collect1 = list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i + j) % 3 == 0)//能被3整除
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        collect1.forEach(c -> System.out.println(Arrays.toString(c)));
    }
}
