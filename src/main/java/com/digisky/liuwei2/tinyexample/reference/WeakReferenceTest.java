package com.digisky.liuwei2.tinyexample.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * 弱应用测试
 *
 * @author liuwei2
 */
public class WeakReferenceTest {


    public static void main(String[] args) {
        ReferenceQueue<Name> rq = new ReferenceQueue<>();

        Name name = new Name();

        WeakReference<Name> weakReference = new WeakReference<>(name, rq);

        // 取消强应用
        name = null;
        System.gc();


        print(name);
        print(weakReference.get());
        print(weakReference.isEnqueued());
    }

}

class Name {}
