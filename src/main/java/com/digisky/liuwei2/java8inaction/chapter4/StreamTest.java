package com.digisky.liuwei2.java8inaction.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.aspectj.util.FileUtil;

import com.google.common.collect.Streams;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * 流测试
 *
 * @author liuwei2
 * @date 2019/10/14 11:56
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> title = Arrays.asList("助教", "讲师", "副教授", "教授");
        Stream<String> stringStream = title.stream();

        // 验证Stream只能被消费一次
        stringStream.forEach(System.out::println);
        // stringStream.forEach(System.out::println);


        // 寻找字符数组里面包含的字符
        String[] words = new String[]{"hello", "world"};
        // 1,仅使用map无法处理
        List<String> list1 = Arrays.stream(words).map(v -> v.split(""))
                .map(Arrays::stream)
                .map(v -> v.distinct().findAny().get())
                .distinct()
                .collect(Collectors.toList());
        print(list1);

        // 2,利用flatMap,进行展开处理
        list1 = Arrays.stream(words).map(v -> v.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        print(list1);

        int[] source = new int[]{1, 2, 3, 4, 5};
        int[] list2 = Arrays.stream(source).map(v -> v * v).toArray();
        print(Arrays.toString(list2));

        List<Integer> first = Arrays.asList(1, 2, 3);
        List<Integer> second = Arrays.asList(4, 5);

        first.stream()
                .flatMap(i -> second.stream().map(j -> new int[]{i, j}))
                .forEach(v -> System.out.printf("%s ", Arrays.toString(v)));

        System.out.println();

        first.stream().
                flatMap(i -> second.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .forEach(v -> System.out.printf("%s ", Arrays.toString(v)));
    }


    private void test() {
        Stream<String> stream;

        // 生成空白的流
        stream = Stream.empty();
        // 通过静态方法生成
        stream = Stream.of("A", "B", "C");
        // 从集合生成流
        List<String> list = new ArrayList<>();
        stream = list.stream();
        // 从数组生成流
        String[] a = new String[]{"A", "B", "C"};
        stream = Arrays.stream(a);
        // 从文件创建流
        try (Stream<String> lines = Files.lines(Paths.get("BasicFileOutput.out"))) {
            System.out.println(lines.flatMap(v -> Arrays.stream(v.split(" "))).count());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 函数生成流
        Stream.iterate(1, n -> n << 1).limit(10).forEach(System.out::println);
        Stream.generate(Math::random).limit(3).forEach(System.out::println);
    }
}
