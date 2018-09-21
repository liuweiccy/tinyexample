package com.digisky.liuwei2.tinyexample.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReflectTest3 {

    public static void main(String[] args) {
        String name;
        // 获取类型字符
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            print("请输入类型名称（例如 java.util.Date）：");
            name = scanner.next();
        }

        try {
            Class<?> cl = Class.forName(name);
            Class<?> superCl = cl.getSuperclass();

            String modifier = Modifier.toString(cl.getModifiers());
            if (modifier.length() > 0) {
                print(modifier + " ");
            }
            print("class " + name);

            if (superCl != null && superCl != Object.class) {
                print(" extends " + superCl.getName());
            }

            print("\n{\n");
            printConstruct(cl);
            print("\n");
            printMethod(cl);
            print("\n");
            printFields(cl);
            print("\n}\n");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printFields(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            System.out.print("    ");
            String modifier = Modifier.toString(field.getModifiers());
            if (modifier.length() > 0) {
                System.out.print(modifier + " ");
            }

            Class<?> fieldType = field.getType();
            print(fieldType + " " + field.getName() + ";");
        }
    }

    private static void printMethod(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("    ");
            String modifier = Modifier.toString(method.getModifiers());
            if (modifier.length() > 0) {
                System.out.print(modifier + " ");
            }

            String returnType = method.getReturnType().getName();
            String name = method.getName();
            System.out.print(returnType + " " + name + "(");

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[j].getName());
            }

            print(");");
        }
    }

    private static void printConstruct(Class<?> cl) {
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            String name = constructor.getName();
            System.out.print("    ");
            String modifier = Modifier.toString(constructor.getModifiers());
            if (modifier.length() > 0) {
                System.out.print(modifier + " ");
            }

            System.out.print(name + "(");

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[j].getName());
            }
            print(");");
        }
    }
}
