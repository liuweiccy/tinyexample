package com.digisky.liuwei2.java8inaction.chapter5;

import java.util.stream.IntStream;

/**
 * 生成勾股定理
 *
 * @author liuwei2
 * @date 2019/10/16 12:06
 */
public class GouGu {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2] % 1 == 0)
        ).limit(10).forEach(v -> System.out.printf("(%.0f, %.0f, %.0f)\n", v[0], v[1], v[2]));
    }
}


