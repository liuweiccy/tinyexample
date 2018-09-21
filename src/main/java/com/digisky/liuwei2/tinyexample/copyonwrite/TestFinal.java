package com.digisky.liuwei2.tinyexample.copyonwrite;

/**
 * @author liuwei2
 */
public class TestFinal {

    public static void main(String[] args) {
        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();

        System.out.println(myClass1.i);
        System.out.println(MyClass.j);

        System.out.println(myClass2.i);
        System.out.println(MyClass.j);
    }
}

class MyClass {
    public final double i = Math.random();
    public static double j = Math.random();
}