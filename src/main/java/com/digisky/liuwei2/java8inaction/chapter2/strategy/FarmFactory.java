package com.digisky.liuwei2.java8inaction.chapter2.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import com.digisky.liuwei2.java8inaction.chapter1.apple.Apple;

/**
 * 农场
 * 参数行为化之类与匿名类
 *
 * @author liuwei2
 * @date 2019/10/08 16:38
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

    static List<Apple> filterApple(ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
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
        print(filterApple(new AppleWeightPredicate()));
        System.out.println("==============================");
        print(filterApple(new AppleColorPredicate()));
        System.out.println("==============================");
        print(filterApple(new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150 && "green".equalsIgnoreCase(apple.getColor());
            }
        }));

        filterApple((Apple apple) ->  apple.getWeight() > 150);

        inventory.sort(Comparator.comparing(Apple::getWeight));

        inventory.sort((a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight()) );

        String ss = "ssdfasdfa";
        Function<Integer, Integer> s= (Integer i) -> ss.indexOf(i);
        Function<Integer, Integer> s1= ss::indexOf;
    }
}
