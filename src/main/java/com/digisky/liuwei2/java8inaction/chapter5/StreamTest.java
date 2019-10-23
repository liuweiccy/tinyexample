package com.digisky.liuwei2.java8inaction.chapter5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * Stream构建
 *
 * @author liuwei2
 * @date 2019/10/17 12:02
 */
public class StreamTest {
    public static void main(String[] args) {
        // 通过普通的值序列来构建流
        Stream.of("Java 8 ", "Lambda ", "In ", "Action").map(String::toUpperCase).forEach(System.out::print);

        // 数值创建流
        print("");
        print(Arrays.stream(new int[]{1, 2, 3, 4}).sum());

        // 从文件创建流
        try (Stream<String> lines = Files.lines(Paths.get("benchmark.log"))) {
            print(lines.flatMap(v -> Arrays.stream(v.split(" "))).count());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 函数生成流
        print(Stream.iterate(1, n -> n + 1).limit(10000).reduce(Integer::sum));
        Stream.iterate(1, n -> n << 1).limit(10).forEach(System.out::println);

        // 无线流的生成
        LongStream.iterate(0, n -> n * 2 + 1).limit(10).forEach(System.out::println);
    }
}
