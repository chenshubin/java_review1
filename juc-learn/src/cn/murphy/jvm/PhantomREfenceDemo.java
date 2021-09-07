package cn.murphy.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用 幽灵引用     与其他引用都不同，虚引用并不会决定对象的生命周期
 * 如果一个对象仅持有虚引用，那么他就和没有任何引用一样，在任何时间都可能被垃圾回收期回收，它不能单独使用，也不能通过他访问对象，虚引用必须和引用队列ReferenceQueue 联合使用
 */
public class PhantomREfenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue  = new ReferenceQueue<>();
        PhantomReference<Object> weakReference = new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        o1 = null ;
        System.gc();
        Thread.sleep(500);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
