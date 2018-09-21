package com.digisky.liuwei2.tinyexample.annotation;

/**
 * @author liuwei2
 */
public class Apple {
    private String type;

    @FruitColor(FruitColor.Color.GREEN)
    private String color;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
