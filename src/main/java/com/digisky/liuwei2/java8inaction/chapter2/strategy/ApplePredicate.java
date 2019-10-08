package com.digisky.liuwei2.java8inaction.chapter2.strategy;

import com.digisky.liuwei2.java8inaction.chapter1.apple.Apple;

/**
 * 策略模式策略接口
 *
 * @author liuwei2
 * @date 2019/10/08 15:54
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
