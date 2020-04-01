package com.digisky.liuwei2.test.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author liuwei2
 * 2020/1/19 11:11
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2)
@Fork(1)
@State(Scope.Benchmark)
@Threads(Threads.MAX)
public class JacksonTest {
    @Param({"10000", "50000", "100000"})
    private int length;


    @Measurement(iterations = 1)
    public void jacksonStringToObject(Blackhole blackhole) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < length; i++) {
            Root root = mapper.readValue(JsonString.JSON, Root.class);
            blackhole.consume(root);
        }
    }

    private Root root;
    @Setup
    public void init() {
        root = JSON.parseObject(JsonString.JSON, Root.class);
    }

    @Benchmark
    @Measurement(iterations = 1)
    public void jacksonObjectToString(Blackhole blackhole) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < length; i++) {
            String json = mapper.writeValueAsString(root);
            blackhole.consume(json);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JacksonTest.class.getSimpleName())
                .build();

        new Runner(options).run();
    }
}
