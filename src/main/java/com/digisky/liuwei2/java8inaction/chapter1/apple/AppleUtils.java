package com.digisky.liuwei2.java8inaction.chapter1.apple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

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

    private static <T> void print(List<T> list) {
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

    /**
     * 函数式编程体现了便捷性，但是性能和非函数式的编程性能存在差异，本例中性能存在10%左右的差异
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(AppleUtils.class.getSimpleName())
                .build();
        new Runner(options).run();

        addApple(new Apple("green", 110));
        addApple(new Apple("green", 170));
        addApple(new Apple("red", 150));
        addApple(new Apple("red", 110));
        addApple(new Apple("yellow", 190));

        // 非函数式写法
        print(filterGreenApples());
        print(filterHeavyApples());
        // 函数式写法
        print(fileApple(AppleUtils::isGreenApple));
        print(fileApple(AppleUtils::isHeavyApple));
    }



}
