package com.learn.interfaces;

import com.learn.entity.Apple;

/**
 * 苹果实体类格式化
 *
 * @author suvue
 * @date 2020/4/23
 */
@FunctionalInterface
public interface AppleFormatter {

    /**
     * 接受苹果方法
     */
    String accept(Apple apple);
}
