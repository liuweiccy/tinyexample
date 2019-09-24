package com.digisky.liuwei2.java8inaction.chapter2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.digisky.liuwei2.java8inaction.chapter1.apple.Apple;

/**
 * 农场
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

    static List<Apple> filterGreenApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= weight) {
                result.add(apple);
            }
        }
        return result;
    }

    // TODO 策略模式实现行为参数化

    private static <T> void print(Collection<T> collection) {
        for (T t : collection) {
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {
        print(filterGreenApple(inventory));

        print(filterAppleByColor(inventory, "red"));
        print(filterAppleByColor(inventory, "green"));
    }
}
