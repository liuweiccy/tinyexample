package com.digisky.liuwei2.java8inaction.chapter6;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 交易信息
 * @author liuwei2
 */
@Data
@AllArgsConstructor
public class Transaction {
    private String city;
    private int year;
    private long amount;
    private String currencyType;

    public boolean isChengdu() {
        return "Chengdu".equals(this.city);
    }
}
