package com.digisky.liuwei2.java8inaction.chapter1.apple;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 苹果
 *
 * @author liuwei2
 * @date 2019/09/23 18:07
 */
@Data
@AllArgsConstructor
public class Apple {
    private String color;
    private int weight;
}
