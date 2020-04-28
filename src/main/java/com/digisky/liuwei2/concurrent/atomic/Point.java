package com.digisky.liuwei2.concurrent.atomic;

/**
 * @author liuwei2
 * 2020/4/28 18:50
 */
public class Point {
    private int x, y;

    public Point(int v) {
        this.x = v;
        this.y = v;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
