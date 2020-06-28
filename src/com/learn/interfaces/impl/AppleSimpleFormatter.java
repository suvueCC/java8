package com.learn.interfaces.impl;

import com.learn.entity.Apple;
import com.learn.interfaces.AppleFormatter;
/**
 * 简单校验
 *
 * @author suvue
 * @date 2020/4/23
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + " g";
    }
}
