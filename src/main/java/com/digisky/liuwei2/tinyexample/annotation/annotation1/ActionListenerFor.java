package com.digisky.liuwei2.tinyexample.annotation.annotation1;

import java.lang.annotation.*;

/**
 * @author liuwei2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionListenerFor {
    String source();
}
