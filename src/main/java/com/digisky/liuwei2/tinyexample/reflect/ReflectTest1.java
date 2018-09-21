package com.digisky.liuwei2.tinyexample.reflect;

import lombok.Data;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * JVM反射学习
 *
 * @author liuwei2
 */
public class ReflectTest1 {
    private static Pattern pattern = Pattern.compile("[Person]");

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Person p = new Person();
        Person p1 = new Person();
        print(p.getClass() == p1.getClass());
        print(p.hashCode());
        print(p1.hashCode());

        print(p.getClass().getName());

        for (Method method : p.getClass().getMethods()) {
            Matcher matcher = pattern.matcher(method.getName());
            if (matcher.find()) {
                print(method);
            }
        }

        Class<?> p3 = Class.forName(p.getClass().getName());
        print(p3.getMethods());

        Class<?> cl = Class.forName("java.util.Random");
        print(cl.getMethods());

        print(p3 == Person.class);

        print(int[].class.getName());
        print(String[].class.getName());
        print(long[].class.getName());

        Object obj = cl.newInstance();
        print(obj);

        Constructor constructor1 = cl.getConstructor();
        try {
            print(constructor1.newInstance());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Constructor constructor2 = cl.getConstructor(long.class);
        try {
            Random random = (Random) constructor2.newInstance(100);
            print(constructor2.newInstance(1000));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Annotation[] annotations = p3.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            print(annotation);
            print(annotation.toString());
            print(annotation.annotationType());
        }
    }
}

@Data
@SuppressWarnings("unchecked")
@Xin
class Person {
    private String id;
    private String name;
    private int age;
    private int height;
    private Address address;



    @Data
    private class Address {
        private String provice;
        private String city;
        private String road;
        private int num;
    }
}


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Xin {
    String value() default "";
}
