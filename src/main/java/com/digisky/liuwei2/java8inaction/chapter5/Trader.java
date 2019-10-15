package com.digisky.liuwei2.java8inaction.chapter5;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 交易员
 *
 * @author liuwei2
 * @date 2019/10/15 20:15
 */
@Data
@AllArgsConstructor
public class Trader {
    private final String name;
    private final String city;
}
