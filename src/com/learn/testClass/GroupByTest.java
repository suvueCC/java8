package com.learn.testClass;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class dog {
    public dog(String name, BigDecimal hairs) {
        this.name = name;
        this.hairs = hairs;
    }

    String name;
    BigDecimal hairs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getHairs() {
        return hairs;
    }

    public void setHairs(BigDecimal hairs) {
        this.hairs = hairs;
    }
}

public class GroupByTest {


    public static void main(String[] args) {
        final dog dog1 = new dog("小三", new BigDecimal(1));
        final dog dog2 = new dog("小三", new BigDecimal(2));
        final dog dog3 = new dog("小四", new BigDecimal(2));
        final dog dog4 = new dog("小四", new BigDecimal(3));
        final List<dog> dogList = Arrays.asList(dog1, dog2, dog3, dog4);
        final Map<String, BigDecimal> map = dogList.stream()
                .collect(Collectors.groupingBy(dog::getName,
                        Collectors.mapping(dog::getHairs, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println(map);
    }
}


