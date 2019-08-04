package com.digisky.liuwei2.tinyexample.serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liuwei2
 */
public class StoreCADState {
    public static void main(String[] args) throws IOException {
        List<Class<? extends  Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Line.class);
        shapeTypes.add(Square.class);

        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shapes.add(Shape.randomFactory());
        }

        for (int i = 0; i < 10; i++) {
            shapes.get(i).setColor(Shape.GREEN);
        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADState.out"));
        out.writeObject(shapeTypes);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);
    }
}

abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);
    public abstract int getColor();

    public Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", dimension=" + dimension +
                '}';
    }

    public static Shape randomFactory() {
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        int dim = rand.nextInt(100);

        switch (counter++ % 3) {
            case 0:
                return new Circle(x, y, dim);
            case 1:
                return new Square(x, y, dim);
            case 2:
                return new Line(x, y, dim);
            default:
                return null;
        }
    }
}

class Circle extends Shape {
    private static int color = RED;

    public Circle(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Square extends Shape {
    private static int color;

    public Square(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Line extends Shape {
    private static int color = RED;

    public static void serializeStaticState(ObjectOutputStream os) throws IOException {
        os.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream os) throws IOException {
        color = os.readInt();
    }

    public Line(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}