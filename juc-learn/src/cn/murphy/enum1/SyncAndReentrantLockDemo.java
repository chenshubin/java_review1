package cn.murphy.enum1;

import sun.net.www.protocol.http.ntlm.NTLMAuthentication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目 多线程之间按照顺序调用，实现 A--》B--》C 三个线程启动，要求如下
 * AA打印5次  bb打印10次 cc打印15次
 * 紧接着
 * AA打印5次  bb打印10次 cc打印15次
 * ...
 * 来10轮
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource data = new ShareResource();
        new Thread(() -> {
            for(int i =1;i<10;i++){
                try {
                    data.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }, "A").start();


        new Thread(() -> {
            for(int i =1;i<10;i++){
                try {
                    data.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }, "B").start();


        new Thread(() -> {
            for(int i =1;i<10;i++){
                try {
                    data.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}



class ShareResource{

    private int number =1 ; //A:1 b:2 c:3

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (number !=1 ){
                condition1.await();
            }
            for(int i = 1 ;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 2;
            condition2.signal();


        }finally {
            lock.unlock();
        }
    }


    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (number !=2 ){
                condition2.await();
            }
            for(int i = 1 ;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 3;
            condition3.signal();


        }finally {
            lock.unlock();
        }
    }


    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (number !=3 ){
                condition3.await();
            }
            for(int i = 1 ;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 1;
            condition1.signal();


        }finally {
            lock.unlock();
        }
    }






}

