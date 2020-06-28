package com.learn.testClass;

import java.util.concurrent.*;
/**
 * future练习
 *
 * @author suvue
 * @date 2020/5/9
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Future<?> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 子线程开始计算");
            Thread.sleep(2999);
            System.out.println(Thread.currentThread().getName() + " 子线程计算结束，准备返回结果");
            return 678;
        });
        System.out.println(Thread.currentThread().getName() + " 主线程开始计算");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " 主线程计算结束");

        try {
            System.out.println(Thread.currentThread().getName() + "线程准备取子线程的结果");
            //如果在指定时间内没取到计算的值，会抛出计算超时的异常
            //加入不设置超时时间，主线程会一直等待下去，这样是不完美的
            final Object o = future.get(1, TimeUnit.SECONDS);
            if (future.isDone()){
                System.out.println("计算完毕！结果为："+o);
            }
            System.out.println(Thread.currentThread().getName() + "线程取出结果为：" + o);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("计算超时");
        }
    }

}
