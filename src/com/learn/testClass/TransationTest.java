package com.learn.testClass;

import com.learn.entity.Trader;
import com.learn.entity.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 交易场景练习Stream流的使用
 *
 * @author suvue
 * @date 2020/4/24
 */
public class TransationTest {
    public static void main(String[] args) {
        final Trader raoul = new Trader("Raoul", "Cambridge");
        final Trader mario = new Trader("Mario", "Milan");
        final Trader alan = new Trader("Alan", "Cambridge");
        final Trader brian = new Trader("Brian", "Cambridge");
        final List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //找出2011年发生的所有交易，并按交易额排序
        final List<Transaction> test1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(test1.toString());

        //交易员都在哪些不同的城市工作过
        final List<String> test2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(test2.toString());

        //查找所有来自剑桥的交易员，并按姓名进行排序
        final List<Trader> test3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> Objects.equals(t.getCity(), "Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(test3.toString());

        //返回所有交易员的姓名字符串，按字母进行排序
        final String test4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);
        System.out.println(test4);

        //有没有交易员是在米兰工作的
        final boolean test5 = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .anyMatch(t -> Objects.equals(t.getCity(), "Milan"));
        System.out.println(test5);

        //打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(t -> Objects.equals(t.getTrader().getCity(), "Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //最大交易额
        final Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(max.get());

        //最小交易额
        final Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        //最小额的另一种求法

        final OptionalInt min1 = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();


        System.out.println(min.get());
        System.out.println(min1.orElse(1));

    }
}
