package com.digisky.liuwei2.tinyexample.annotation;

import java.lang.annotation.*;

/**
 * @author liuwei2
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {
    enum Color {
        /** 绿色 */
        GREEN,
        /** 红色 */
        RED,
        /** 蓝色 */
        BLUE,
    }

    Color value();
}
