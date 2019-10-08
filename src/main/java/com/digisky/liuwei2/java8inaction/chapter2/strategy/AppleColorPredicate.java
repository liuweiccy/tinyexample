package com.digisky.liuwei2.java8inaction.chapter2.strategy;

import com.digisky.liuwei2.java8inaction.chapter1.apple.Apple;

/**
 * 颜色策略
 *
 * @author liuwei2
 * @date 2019/10/08 16:18
 */
public class AppleColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }
}
