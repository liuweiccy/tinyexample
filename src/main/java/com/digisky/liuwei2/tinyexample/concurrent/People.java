package com.digisky.liuwei2.tinyexample.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liuwei2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class People {
    private String name;
    private int age;
}
