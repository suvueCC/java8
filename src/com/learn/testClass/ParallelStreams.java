package com.learn.testClass;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流学习
 *
 * @author suvue
 * @date 2020/4/25
 */
public class ParallelStreams {
    /**
     * 传统的迭代
     */
    private static long iterativeSum(long n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 顺序流
     */
    private static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 并行流
     */
    private static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 采用数据范围生成数据源,采用并行方式
     */
    private static long rangeSum(long n) {
        return LongStream.rangeClosed(1L, n)
                .parallel()
                .reduce(0, Long::sum);
    }

    /**
     * 测试性能
     */
    private static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            final long start = System.nanoTime();
            final Long sum = adder.apply(n);
            final long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;

    }

    public static void main(String[] args) {
        System.out.println("Sequential sum done in:" + measureSumPerf(ParallelStreams::sequentialSum, 10000000) + " " +
                "msecs");
        System.out.println("Iterative sum done in:" + measureSumPerf(ParallelStreams::iterativeSum, 10000000) + " " +
                "msecs");
        System.out.println("Parallel sum done in:" + measureSumPerf(ParallelStreams::parallelSum, 10000000) + " msecs");
        System.out.println("Range sum done in:" + measureSumPerf(ParallelStreams::rangeSum, 10000000) + " msecs");
    }

}
