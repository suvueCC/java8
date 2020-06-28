package com.learn.testClass;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 勾股数练习
 *
 * @author suvue
 * @date 2020/4/24
 */
public class PythagorasTest {
    public static void main(String[] args) {
        int a = 3;
        //写法1
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

        //写法2
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

        //以上是在a提前给定的情况，下面写出a也是随机生成的情况
        final Stream<int[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a1 -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a1 * a1 + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a1, b, (int) Math.sqrt(a1 * a1 + b * b)}));
        stream.limit(5)
                .forEach(t -> System.out.println(t[0] + " , " + t[1] + "  " + t[2]));

        //进一步优化，使得代码更为紧凑
        final Stream<double[]> stream1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a2 -> IntStream.rangeClosed(a2, 100)
                        .mapToObj(b -> new double[]{a2, b, Math.sqrt(a2 * a2 + b * b)})
                        .filter(array -> array[2] % 1 == 0));


    }
}
