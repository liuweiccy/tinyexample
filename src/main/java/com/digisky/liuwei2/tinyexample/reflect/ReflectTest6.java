package com.digisky.liuwei2.tinyexample.reflect;

import java.lang.reflect.Array;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReflectTest6 {

    public static void main(String[] args) {
        String[] names = new String[]{"vv", "yy", "xin"};
        String[] newNames = (String[]) arrayCopy(names, 2 * names.length);
        print(newNames);
    }

    public static Object arrayCopy(Object obj, int newLength) {
        Class objClass = obj.getClass();
        if (!objClass.isArray()) {
            throw new IllegalArgumentException("obj must be a array");
        }
        if (Array.getLength(obj) >= newLength) {
            throw new IllegalArgumentException("newLength must be gt original len");
        }
        Class componentType = objClass.getComponentType();
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(obj, 0, newArray, 0, Array.getLength(obj));
        return newArray;
    }
}
