package com.digisky.liuwei2.java8inaction.chapter1.apple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 苹果操作类
 *
 * @author liuwei2
 * @date 2019/09/23 18:09
 */
@State(Scope.Benchmark)
public class AppleUtils {
    private static List<Apple> inventory = new ArrayList<>();

    public static void addApple(Apple apple) {
        inventory.add(apple);
    }

    /**
     * 非函数式选取绿色苹果
     * @return  绿色苹果
     */
    public static List<Apple> filterGreenApples() {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 非函数式选取大于指定重量的苹果
     * @return  指定重量的苹果
     */
    public static List<Apple> filterHeavyApples() {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }


    public static boolean isGreenApple(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> fileApple(Predicate<Apple> applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    static <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        for (T t : collection) {
            if (predicate.test(t)) {
                list.add(t);
            }
        }
        return list;
    }

    private static <T> void print(Collection<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }

    @Setup
    public void init() {
        addApple(new Apple("green", 110));
        addApple(new Apple("green", 170));
        addApple(new Apple("red", 150));
        addApple(new Apple("red", 110));
        addApple(new Apple("yellow", 190));
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @Threads(Threads.MAX)
    @Fork(3)
    @Warmup(iterations = 3, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkNonFunc() {
        // 非函数式写法
        filterGreenApples();
        filterHeavyApples();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @Threads(Threads.MAX)
    @Fork(3)
    @Warmup(iterations = 3, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkFunc() {
        // 函数式写法
        fileApple(AppleUtils::isGreenApple);
        fileApple(AppleUtils::isHeavyApple);
    }


    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @Threads(Threads.MAX)
    @Fork(3)
    @Warmup(iterations = 3, time = 1)
    @Measurement(iterations = 5, time = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmarkParFunc() {
        // 函数式写法
        inventory.parallelStream().filter(v -> v.getWeight() > 150).collect(Collectors.toList());
        inventory.parallelStream().filter(v -> "green".equalsIgnoreCase(v.getColor())).collect(Collectors.toList());
    }

    /**
     * 数据的测试量不大，5条数据的集合处理
     * 函数式编程体现了便捷性，但是性能和非函数式的编程性能存在差异，本例中性能存在10%左右的差异
     * 并行模式下的性能并不高，具体的性能数据如下
     * Benchmark                     Mode  Cnt      Score      Error   Units
     * AppleUtils.benchmarkFunc     thrpt   15  76629.804 ± 2222.932  ops/ms
     * AppleUtils.benchmarkNonFunc  thrpt   15  84729.607 ± 2680.693  ops/ms
     * AppleUtils.benchmarkParFunc  thrpt   15   2731.873 ±  150.064  ops/ms
     */
    public static void main(String[] args) throws RunnerException {
        new AppleUtils().init();
        Options options = new OptionsBuilder()
                .include(AppleUtils.class.getSimpleName())
                .build();
        new Runner(options).run();

        // 非函数式写法
        print(filterGreenApples());
        print(filterHeavyApples());
        // 函数式写法
        print(fileApple(AppleUtils::isGreenApple));
        print(fileApple(AppleUtils::isHeavyApple));


        // 匿名Lambda写法（适用逻辑简短，如果逻辑复杂的话使用方法引用的方式进行书写）-- 以代码的清晰度为依据
        print(fileApple((Apple apple) -> apple.getWeight() > 170));
        print(filter(inventory, AppleUtils::isHeavyApple));
        print(inventory.stream().filter(v -> v.getWeight() > 150).collect(Collectors.toList()));
        print(inventory.parallelStream().filter(v -> v.getWeight() > 150).collect(Collectors.toList()));
    }
}
