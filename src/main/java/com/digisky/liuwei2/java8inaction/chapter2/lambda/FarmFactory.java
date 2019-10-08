package com.digisky.liuwei2.java8inaction.chapter2.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.digisky.liuwei2.java8inaction.chapter1.apple.Apple;

/**
 * 农场
 * 参数行为化的第三种方式：lambda
 * 其他的两种方式 类与匿名类，参见策略模式
 * @see com.digisky.liuwei2.java8inaction.chapter2.strategy.FarmFactory
 *
 * @author liuwei2
 * @date 2019/09/24 18:05
 */
public class FarmFactory {
    private static List<Apple> inventory;

    static {
        inventory = new ArrayList<>();
        inventory.add(new Apple("red", 130));
        inventory.add(new Apple("green", 130));
        inventory.add(new Apple("red", 170));
        inventory.add(new Apple("green", 180));
        inventory.add(new Apple("red", 110));
        inventory.add(new Apple("green", 150));
        inventory.add(new Apple("red", 140));
        inventory.add(new Apple("green", 190));
    }

    static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>(10);
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }

        return result;
    }

    private static <T> void print(Collection<T> collection) {
        for (T t : collection) {
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {
        print(filterApple(inventory, (Apple apple) -> apple.getWeight() > 150));
        System.out.println("=============================================");
        print(filterApple(inventory, (Apple apple) -> "green".equalsIgnoreCase(apple.getColor())));
        System.out.println("=============================================");
        print(filterApple(inventory, (Apple apple) -> "green".equalsIgnoreCase(apple.getColor()) && apple.getWeight() > 150));
        System.out.println("====================抽象化=========================");
        print(filter(inventory, (Apple apple) -> "green".equalsIgnoreCase(apple.getColor()) && apple.getWeight() > 150));

        System.out.println("=====================重量排序========================");
        // 匿名类的方式，按照重量来进行排序
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });
        print(inventory);


        System.out.println("======================颜色排序=======================");
        // lambda的方式，按照颜色进行排序
        inventory.sort(Comparator.comparing(Apple::getColor));
        print(inventory);
    }
}
