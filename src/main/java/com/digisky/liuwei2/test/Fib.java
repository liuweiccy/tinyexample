package com.digisky.liuwei2.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Fib性能测试
 *
 * @author Eric Liu
 * @date 2019/10/04 11:21
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 3)
@Fork(3)
@Threads(Threads.MAX)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Fib{
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Fib.class.getSimpleName())
                .build();

        new Runner(options).run();
    }

    @Benchmark
    public void test() {
        fibonacci(34);
    }

    static int fibonacci( int i){
        if (i < 2) {
            return i;
        }
        return fibonacci(i-2) + fibonacci(i-1);
    }
}
