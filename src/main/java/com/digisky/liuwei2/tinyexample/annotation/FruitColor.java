package com.digisky.liuwei2.tinyexample.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
