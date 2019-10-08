package com.digisky.liuwei2.java8inaction.chapter2.strategy;

import com.digisky.liuwei2.java8inaction.chapter1.apple.Apple;

/**
 * 重量策略
 *
 * @author liuwei2
 * @date 2019/10/08 16:17
 */
public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
