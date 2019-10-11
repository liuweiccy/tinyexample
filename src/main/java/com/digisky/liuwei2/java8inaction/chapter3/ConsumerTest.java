package com.digisky.liuwei2.java8inaction.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * Consumer<T> 函数式接口示例
 *
 * @author liuwei2
 * @date 2019/10/10 10:31
 */
public class ConsumerTest {

    static <T> void dealList(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    static <T, R> List<R> dealList2(List<T> list, Function<T, R> function) {
        List<R> newList = new ArrayList<>(list.size());
        for (T t : list) {
            newList.add(function.apply(t));
        }
        return newList;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(3);
        list.add("VV");
        list.add("YY");
        list.add("XX");

        dealList(list, (String str) -> System.out.println(str));
        dealList(list, System.out::println);
        dealList(list, String::valueOf);

        List<String> list2 = dealList2(list, (String str) -> str = "name:" + str);
        print(list2);
    }
}
