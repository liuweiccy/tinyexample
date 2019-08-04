package com.digisky.liuwei2.tinyexample.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.Setter;
import lombok.ToString;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * JVM反射
 *
 * @author liuwei2
 */
public class ReflectTest2 {
    public static void main(String[] args) {

        try {
            Class<?> studentClass = Class.forName("com.digisky.liuwei2.tinyexample.reflect.Student");

            Constructor<?> constructor = studentClass.getConstructor(String.class, String.class, String.class);
            print(constructor);

            Constructor<?>[] constructors = studentClass.getConstructors();
            print(constructors);

            Field[] fields = studentClass.getFields();
            print(fields);
            Field[] fields1 = studentClass.getDeclaredFields();
            print(fields1);

            Method[] methods = studentClass.getMethods();
            print(methods);

            Class[] classes = studentClass.getDeclaredClasses();
            print(classes);

            Object student = constructor.newInstance("1025", "xin", "6.1");

            print(studentClass.getModifiers());



            print("Hello, " + student);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

@Setter
@ToString
class Student {
    private String no;
    private String name;
    private String grade;
    private Address address;

    public String level;

    public Student(String no, String name, String grade) {
        this.no = no;
        this.name = name;
        this.grade = grade;
        this.level = "A";
    }

    public Student() {
    }


    private class Address {
    }
}
