package com.digisky.liuwei2.tinyexample.thread;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class ThreadGroupStudy {
    public static void main(String[] args) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        print(currentGroup.getName());

        ThreadGroup group1 = new ThreadGroup("group1");
        print(group1.getParent() == currentGroup);

        ThreadGroup group2 = new ThreadGroup(group1, "group2");
        print(group1 == group2.getParent());
    }
}
