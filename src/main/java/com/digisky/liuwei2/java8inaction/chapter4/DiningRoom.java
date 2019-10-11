package com.digisky.liuwei2.java8inaction.chapter4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * 餐厅
 *
 * @author liuwei2
 * @date 2019/10/11 18:07
 */
public class DiningRoom {
    private static List<Dish> menu = new ArrayList<>();
    static {
        menu.add(new Dish("凉拌番茄", 50, 10f));
        menu.add(new Dish("凉拌白肉", 650, 28f));
        menu.add(new Dish("凉拌鸡片", 500, 22f));

        menu.add(new Dish("炒回锅肉", 800, 18f));
        menu.add(new Dish("鱼香茄子", 600, 28f));
        menu.add(new Dish("炒蔬菜", 100, 8f));

        menu.add(new Dish("水煮鱼", 1000, 48f));
        menu.add(new Dish("红烧肉", 1200, 36f));
        menu.add(new Dish("炖牛肉", 1500, 98f));

        menu.add(new Dish("豆腐汤", 50, 10f));
        menu.add(new Dish("蛋花汤", 50, 10f));
        menu.add(new Dish("炖猪蹄", 800, 40f));
        menu.add(new Dish("羊肉汤", 1500, 98f));
    }

    static List<String> lowerCaloriesNameJava7(int caloriesValue) {
        List<Dish> lowerCalories = new ArrayList<>(menu.size());
        for (Dish dish : menu) {
            if (dish.getCalories() <= caloriesValue) {
                lowerCalories.add(dish);
            }
        }

        lowerCalories.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowerCaloriesNames = new ArrayList<>(lowerCalories.size());
        for (Dish dish : lowerCalories) {
            lowerCaloriesNames.add(dish.getName());
        }

        return lowerCaloriesNames;
    }

    static List<String> lowerCaloriesNameJava8(int caloriesValue) {
        return menu.stream().filter(v -> v.getCalories() <= caloriesValue)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        print("==================传统写法======================");
        print(lowerCaloriesNameJava7(500));

        print("==================Java8 Stream ================");
        print(lowerCaloriesNameJava8(500));
    }
}
