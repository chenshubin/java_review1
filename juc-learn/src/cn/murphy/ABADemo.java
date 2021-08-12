package cn.murphy;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

//    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    //要注意Integer的127的界限
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
//ABA问题的产生
//        new Thread(()->{
//            atomicReference.compareAndSet(100,101);
//            atomicReference.compareAndSet(101,100);
//        },"t1").start();
//
//        new Thread(()->{
//            try {
//                //暂停1秒
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get());
//
//        },"t2").start();


        ////----------------------ABA问题的解决----------------
        new Thread(()->{
           int stamp =  atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t1当前版本号"+stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(100,101,Integer.valueOf(atomicStampedReference.getStamp()),Integer.valueOf(atomicStampedReference.getStamp()+1)));
            System.out.println(Thread.currentThread().getName()+"\t2当前版本号"+atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.getReference());
            System.out.println(atomicStampedReference.getReference()+","+atomicStampedReference.getStamp());


            System.out.println( atomicStampedReference.compareAndSet(101,100,Integer.valueOf(atomicStampedReference.getStamp()),Integer.valueOf(atomicStampedReference.getStamp()+1)));
            System.out.println(Thread.currentThread().getName()+"\t3当前版本号"+atomicStampedReference.getStamp());
            },"t3").start();


        new Thread(()->{
            int stamp =  atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t当前版本号"+stamp);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag =  atomicStampedReference.compareAndSet(100,2048,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t是否修改成功"+flag);


            System.out.println(Thread.currentThread().getName()+"\t当前最新值"+atomicStampedReference.getReference());

        },"t4").start();

    }


}
