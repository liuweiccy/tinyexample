package com.digisky.liuwei2.tinyexample.generic.generic1;

import lombok.Data;

/**
 * @author liuwei2
 */
@Data
public class Employee {
    private String name;
    private int bonus;

    public Employee(String name, int bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    public Employee() {
    }
}
