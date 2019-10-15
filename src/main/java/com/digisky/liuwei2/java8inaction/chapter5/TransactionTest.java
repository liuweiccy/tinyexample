package com.digisky.liuwei2.java8inaction.chapter5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 交易测试
 *
 * @author liuwei2
 * @date 2019/10/15 20:22
 */
public class TransactionTest {
    private static final List<Transaction> TRANSACTION_LIST = new ArrayList<>();

    static {
        Trader raoul = new Trader("Raoul", "Chengdu");
        Trader mario = new Trader("Mario", "Chengdu");
        Trader alan = new Trader("Alan", "Chengdu");
        Trader brian = new Trader("Brian", "Beijing");

        TRANSACTION_LIST.add(new Transaction(brian, 2011, 300));
        TRANSACTION_LIST.add(new Transaction(raoul, 2012, 1000));
        TRANSACTION_LIST.add(new Transaction(raoul, 2011, 400));
        TRANSACTION_LIST.add(new Transaction(mario, 2012, 710));
        TRANSACTION_LIST.add(new Transaction(mario, 2012, 700));
        TRANSACTION_LIST.add(new Transaction(alan, 2012, 950));
    }

    public static void main(String[] args) {
        System.out.println("==========2011年的所有交易，按交易额从低到高排序===========");
        TRANSACTION_LIST.stream().filter(v -> v.getYear() == 2011).sorted(Comparator.comparing(Transaction::getAmount)).forEach(System.out::println);
        System.out.println("==========交易员再那些不同的城市工作过====================");
        TRANSACTION_LIST.stream().map(v -> v.getTrader().getCity()).distinct().forEach(System.out::println);
        System.out.println("==========来自成都的交易员，按照名字排序==================");
        TRANSACTION_LIST.stream().filter(v -> "Chengdu".equals(v.getTrader().getCity())).map(v -> v.getTrader().getName()).distinct().sorted(String::compareTo).forEach(System.out::println);
        System.out.println("==========所有交易员按名字排序===========================");
        TRANSACTION_LIST.stream().map(v -> v.getTrader().getName()).distinct().sorted(String::compareTo).forEach(System.out::println);
        System.out.println("==========有没有交易员再Chengdu工作======================");
        System.out.println(TRANSACTION_LIST.stream().anyMatch(v -> "Chengdu".equals(v.getTrader().getCity())));
        System.out.println("==========生活在Beijing的交易员的所有交易额===============");
        TRANSACTION_LIST.stream().filter(v -> "Beijing".equals(v.getTrader().getCity())).map(Transaction::getAmount).forEach(System.out::println);
        System.out.println("==========最高交易额====================================");
        System.out.println(TRANSACTION_LIST.stream().map(Transaction::getAmount).max(Integer::compareTo).orElse(0));
        System.out.println("==========最小交易额，的交易=============================");
        System.out.println(TRANSACTION_LIST.stream().reduce((t1, t2) -> t1.getAmount() < t2.getAmount() ? t1 : t2).orElse(null));
    }
}
