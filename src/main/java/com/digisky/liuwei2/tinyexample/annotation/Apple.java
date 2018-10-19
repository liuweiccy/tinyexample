package com.digisky.liuwei2.tinyexample.annotation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liuwei2
 */
public class Apple {
    @Getter
    @Setter
    private String type;

    @FruitColor(FruitColor.Color.GREEN)
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
