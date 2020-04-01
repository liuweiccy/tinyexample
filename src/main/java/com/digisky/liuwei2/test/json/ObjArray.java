package com.digisky.liuwei2.test.json;

/**
 * @author liuwei2
 * 2020/1/19 11:06
 */
public class ObjArray {
    private String className;
    private int age;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ObjArray{" +
                "className='" + className + '\'' +
                ", age=" + age +
                '}';
    }
}
