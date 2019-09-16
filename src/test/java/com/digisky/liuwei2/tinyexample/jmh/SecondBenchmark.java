package com.digisky.liuwei2.tinyexample.jmh;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 自然数求和的串行与并行的基准测试
 *
 * @author liuwei2
 * @date 2019/09/16 16:51
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class SecondBenchmark {
    @Param({"10000", "100000", "1000000"})
    private int length;

    private int[] numbers;
    private Calculator singleCalc;
    private Calculator multiCalc;

    @Setup
    public void prepare() {
        numbers = IntStream.rangeClosed(1, length).toArray();
        singleCalc = new SingleCalculator();
        multiCalc = new MultiCalculator(Runtime.getRuntime().availableProcessors());
    }

    @Benchmark
    public long singleBench() {
        return singleCalc.sum(numbers);
    }

    @Benchmark
    public long multiBench() {
        return multiCalc.sum(numbers);
    }

    @TearDown
    public void destroy() {
        singleCalc.shutdown();
        multiCalc.shutdown();
    }

    @Test
    public void test() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SecondBenchmark.class.getSimpleName())
                .output("./benchmark_2.log")
                .build();

        new Runner(options).run();
    }
}
