package com.digisky.liuwei2.java8inaction.chapter6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * 测试交易行为的数据收集
 * @author liuwei2
 * @date 2019-11-5 22:43
 */
public class TestTransactionStream {
    private static List<Transaction> list = new ArrayList<>(10);

    static {
        list.add(new Transaction("Chengdu", 2010, 1000, "RMB"));
        list.add(new Transaction("Shanghai", 2012, 700, "USD"));
        list.add(new Transaction("Chengdu", 2019, 100, "USD"));
        list.add(new Transaction("Chengdu", 2019, 1400, "RMB"));
        list.add(new Transaction("Beijing", 2014, 900, "RMB"));
        list.add(new Transaction("Beijing", 2016, 2000, "EUR"));
    }

    public static void main(String[] args) {
        print("===================按货币类型分组==========================");
        Map<String, List<Transaction>> group = list.stream().collect(Collectors.groupingBy(Transaction::getCurrencyType));
        print(group);
        print("===================交易的数据数量==========================");
        // 可以使用count方法代替
        print(list.stream().collect(Collectors.counting()));

        print("===================最大交易额==========================");
        // 可以直接使用max
        Optional<Transaction> transactionMax = list.stream().collect(Collectors.maxBy(Comparator.comparingLong(Transaction::getAmount)));
        print(transactionMax.get());
        print("===================最小交易额==========================");
        // 可以直接使用min
        Optional<Transaction> transactionMin = list.stream().collect(Collectors.minBy(Comparator.comparingLong(Transaction::getAmount)));
        print(transactionMin.get());
        print("===================汇总：求和==========================");
        print(list.stream().collect(Collectors.summingLong(Transaction::getAmount)));
        print("===================汇总：平均==========================");
        print(list.stream().collect(Collectors.averagingLong(Transaction::getAmount)));
        print("===================汇总：概要==========================");
        print(list.stream().collect(Collectors.summarizingLong(Transaction::getAmount)));
        print("===================连接==========================");
        print(list.stream().map(Transaction::getCity).distinct().collect(Collectors.joining(" ")));
        print("===================按照城市进行分组=================");
        print(list.stream().collect(Collectors.groupingBy(Transaction::getCity)));
        print("===================自定义分类函数的分组==================");
        print(list.stream().collect(Collectors.groupingBy(transaction -> {
            if (transaction.getAmount() >= 1000) {
                return "High";
            } else {
                return "Low";
            }
        })));
        print(list.stream().collect(Collectors.groupingBy(transaction -> {
            if (transaction.getAmount() >= 1000) {
                return "High";
            } else {
                return "Low";
            }
        }, Collectors.counting())));

        print("===================先按照城市再按照金额高低(多级分组)=======================");
        print(list.stream().collect(Collectors.groupingBy(Transaction::getCity, Collectors.groupingBy(transaction -> {
            if (transaction.getAmount() >= 1000) {
                return "High";
            } else {
                return "Low";
            }
        }, Collectors.counting()))));
    }
}
