package cn.murphy.jvm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 引用队列，会在回收的时候塞进去
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue  = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());


        o1=null;
        System.gc();
        Thread.sleep(500);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
