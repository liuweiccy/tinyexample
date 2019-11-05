package com.digisky.liuwei2.java8inaction.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        print("===================按城市分组==========================");
        Map<String, List<Transaction>> group = list.stream().collect(Collectors.groupingBy(Transaction::getCurrencyType));
        print(group);
    }
}
