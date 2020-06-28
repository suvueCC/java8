package com.learn.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 商店类
 *
 * @author suvue
 * @date 2020/5/9
 */
public class Shop {
    private String product;

    public Shop(String product) {
        this.product = product;
    }

    public Future<Double> getPriceAsync() {
        //工厂模式实现 代码更为简单
        return CompletableFuture.supplyAsync(() -> caculateProce(product));
        /*final CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                final double proce = caculateProce(product);
                completableFuture.complete(proce);
            } catch (Exception e) {
                //会将发生的异常抛出
                completableFuture.completeExceptionally(e);
                e.printStackTrace();
            }
        }).start();
        //无需等待还没结束的计算,直接返回Future对象
        return completableFuture;*/
    }

    public String getProduct() {
        return product;
    }

    public double caculateProce() {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public double caculateProce(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟耗时操作
     */
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试代码
     *
     * @author suvue
     * @date 2020/5/9
     */
    public static void main(String[] args) {
        //test1();
        final ArrayList<Shop> shops = new ArrayList<>(Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("BuyItAll1"),
                new Shop("BuyItAll2"),
                new Shop("finally")));
        final long start = System.nanoTime();
        final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        final Thread thread = new Thread(r);
                        //设为守护线程
                        thread.setDaemon(true);
                        return thread;
                    }
                });
        //并行流 时间为1秒左右
       /* shops.stream()
                .parallel()
                .map(shop -> String.format("%s price is %.2f",shop.getProduct(),shop.caculateProce()))
                .forEach(System.out::println);*/
        final List<CompletableFuture<String>> futureList = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getProduct() + "price is " + shop.caculateProce(),executor))
                .collect(Collectors.toList());
        futureList.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
        System.out.println("总耗时为：" + (System.nanoTime() - start) / 1000000 + "毫秒");

    }

    private static void test1() throws InterruptedException {
        final Shop shop = new Shop("best Shop");
        final long start = System.nanoTime();
        final Future<Double> priceAsync = shop.getPriceAsync();
        final long search1 = (System.nanoTime() - start) / 1000000;
        System.out.println("查询商店1总共耗时：" + search1 + " 毫秒");

        //线程阻塞，模拟查询其他店铺产品价格的操作
        Thread.sleep(2000L);

        //所以查询完成之后，获取返回的价格
        try {
            final Double price = priceAsync.get();
            System.out.println("商店1查到的价格为：" + price);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("总共耗时：" + (System.nanoTime() - start) / 1000000 + " 毫秒");
    }
}
