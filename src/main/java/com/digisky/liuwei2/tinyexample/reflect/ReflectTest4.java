package com.digisky.liuwei2.tinyexample.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReflectTest4 {
    public static void main(String[] args) {
        Employee harry = new Employee("harry", 35000, 1989);
        Class harryClass = harry.getClass();
        try {
            Field field = harryClass.getDeclaredField("name");
            field.setAccessible(true);
            String name = (String) field.get(harry);
            print(name);

            Field field1 = harryClass.getDeclaredField("salary");
            field1.setAccessible(true);
            field1.set(harry, 40000.00);
            double salary = field1.getDouble(harry);
            print(salary);

            Method method = harryClass.getMethod("getBirthday");
            Object object = method.invoke(harry);
            print(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

@Data
@AllArgsConstructor
class Employee {
    private String name;
    private double salary;
    private int birthday;
}
