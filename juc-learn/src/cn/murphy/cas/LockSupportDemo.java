package cn.murphy.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    static Object objectLock = new Object();




    public static void main(String[] args) {
//        tr();

//        LockSupport lockSupport = new LockSupport();

        Thread a = 
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

                System.out.println(Thread.currentThread().getName()+"\t"+"-------come in");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+"\t"+"-------被唤醒");
        }, "A");

        a.start();

       Thread b =  new Thread(() -> {

                System.out.println(Thread.currentThread().getName()+"\t"+"-------唤醒动作");
                 LockSupport.unpark(a);
       }, "B");
        b.start();

    }

    private static void tr() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock){
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(Thread.currentThread().getName()+"\t"+"-------come in");

                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"-------被唤醒");
            }
        }, "A").start();


        new Thread(() -> {
            synchronized (objectLock){

                System.out.println(Thread.currentThread().getName()+"\t"+"-------唤醒动作");

                    objectLock.notify();
            }
        }, "B").start();
    }
}
