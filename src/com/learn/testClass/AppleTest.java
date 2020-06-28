package com.learn.testClass;

import com.learn.entity.Apple;
import com.learn.interfaces.AppleFormatter;
import com.learn.interfaces.impl.AppleFancyFormatter;
import com.learn.interfaces.impl.AppleSimpleFormatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 苹果问题测试类
 *
 * @author suvue
 * @date 2020/4/23
 */
public class AppleTest {
    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 50));
        apples.add(new Apple("blue", 100));
        apples.add(new Apple("yellow", 200));
        apples.add(new Apple("green", 250));
        new AppleTest().filterApple(apples, new AppleSimpleFormatter());
        System.out.println("------------------------------");
        new AppleTest().filterApple(apples, new AppleFancyFormatter());
        new AppleTest().filterApple(apples, apple -> "看一下匿名内部类");

        //对苹果排序
        apples.sort(Comparator.comparingInt(Apple::getWeight));
        //两个苹果一样重时，按照名称排序
        apples.sort(Comparator.comparing(Apple::getColor)
                .reversed()//降序
                .thenComparing(Apple::getColor));
    }

    public void filterApple(List<Apple> apples, AppleFormatter formatter) {
        for (Apple apple : apples) {
            String accept = formatter.accept(apple);
            System.out.println(accept);
        }
    }
}
