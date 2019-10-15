package com.digisky.liuwei2.java8inaction.chapter5;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 交易
 *
 * @author liuwei2
 * @date 2019/10/15 20:16
 */
@Data
@AllArgsConstructor
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int amount;
}
