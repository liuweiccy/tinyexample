package com.digisky.liuwei2.tinyexample.finalstatic;

public class TestFinal {
    public TestFinal(String name) {
        this.name = name;
    }

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        TestFinal testFinal = new TestFinal("Java");
        System.out.println(testFinal.toString().toLowerCase());
    }
}
