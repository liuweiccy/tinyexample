package com.digisky.liuwei2.tinyexample.jmh;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 字符串连接Benchmark
 *
 * @author liuwei2
 * @date 2019/09/16 15:14
 */
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Threads(1)
@Fork(2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringAddTest {

    @Benchmark
    public void testStringAdd() {
        String s = "";
        for (int i = 0; i < 10; i++) {
            s += i;
        }
        print(s);
    }

    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        print(sb.toString());
    }

    @Test
    public void test() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StringAddTest.class.getSimpleName())
                .output("./benchmark.log")
                .build();

        new Runner(options).run();
    }


    private void print(String s) {}
}
