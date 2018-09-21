package com.digisky.liuwei2.tinyexample.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ReflectTest5 {
    public static void main(String[] args) {
        ArrayList<Integer> squars = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            squars.add(i << i);
        }
        print(new ObjectAnalyzer().toString(1));
    }
}

class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object object) {
        if (object == null) {
            return "null";
        }

        if (visited.contains(object)) {
            return "...";
        }
        visited.add(object);

        Class cl = object.getClass();
        if (cl == String.class) {
            return (String) object;
        }
        if (cl.isArray()) {
            StringBuilder r = new StringBuilder(cl.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(object); i++) {
                if (i > 0) {
                    r.append(", ");
                }
                Object val = Array.get(object, i);
                if (cl.getComponentType().isPrimitive()) {
                    r.append(val);
                } else {
                    r.append(toString(val));
                }
            }
            r.append("}");
            return r.toString();
        }

        StringBuilder r = new StringBuilder(cl.getName());
        do {
            r.append("[");
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.toString().endsWith("[")) {
                        r.append(",");
                    }
                    r.append(field.getName()).append("=");

                    Class type = field.getType();
                    try {
                        Object val = field.get(object);
                        if (type.isPrimitive()) {
                            r.append(val);
                        } else {
                            r.append(toString(val));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            r.append("]");
            cl = cl.getSuperclass();
        } while (cl != null);

        return r.toString();
    }


}
