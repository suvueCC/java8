package com.learn.interfaces.impl;

import com.learn.entity.Apple;
import com.learn.interfaces.AppleFormatter;

/**
 * 复杂校验
 *
 * @author suvue
 * @date 2020/4/23
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
