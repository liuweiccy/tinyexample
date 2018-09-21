package com.digisky.liuwei2.tinyexample.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReflectTest7 {
    public static void main(String[] args) {
        Class mathClass = Math.class;
        try {
            Method method = mathClass.getMethod("sqrt", double.class);
            StringBuilder builder = new StringBuilder(Modifier.toString(method.getModifiers()));
            builder.append(" ");
            builder.append(method.getReturnType().getName());
            builder.append(" ");
            builder.append(method.getName());
            builder.append("(");
            Class[] parameterTypes = method.getParameterTypes();
            for (Class clazz : parameterTypes) {
                if (!builder.toString().endsWith("(")) {
                    builder.append(", ");
                }
                builder.append(clazz.getName());
            }
            builder.append(")");
            print(builder.toString());

            for (double x = 1.0; x <= 10; x++) {
                double y = (double) method.invoke(null, x);
                System.out.printf("%8.4f | %10.4f\n", x, y);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
