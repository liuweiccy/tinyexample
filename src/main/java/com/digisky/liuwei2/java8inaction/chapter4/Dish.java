package com.digisky.liuwei2.java8inaction.chapter4;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 菜名
 *
 * @author liuwei2
 * @date 2019/10/11 18:03
 */
@Data
@AllArgsConstructor
public class Dish {
    private String name;
    private int calories;
    private float price;
    private Type type;

    enum Type {
        /** 凉菜 */
        LianCai,
        /** 炒菜 */
        ChaoCai,
        /** 烧菜 */
        ShaoCai,
        /** 汤菜 */
        TangCai,
    }
}
