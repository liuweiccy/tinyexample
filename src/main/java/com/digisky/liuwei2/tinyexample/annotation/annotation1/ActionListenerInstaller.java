package com.digisky.liuwei2.tinyexample.annotation.annotation1;

import java.awt.event.ActionListener;
import java.lang.reflect.*;

/**
 * @author liuwei2
 */
public class ActionListenerInstaller {
    public static void processAnnotations(Object obj) {
        Class<?> cl = obj.getClass();

        for (Method method : cl.getDeclaredMethods()) {
            ActionListenerFor a = method.getAnnotation(ActionListenerFor.class);
            if (a != null) {
                try {
                    Field f = cl.getDeclaredField(a.source());
                    f.setAccessible(true);
                    addListener(f.get(obj), obj, method);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addListener (Object source, final Object param, final Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InvocationHandler handler = (proxy, m, args) -> m.invoke(proxy, args);
        Object listener = Proxy.newProxyInstance(null, new Class[] {java.awt.event.ActionListener.class}, handler);
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source, listener);
    }
}
