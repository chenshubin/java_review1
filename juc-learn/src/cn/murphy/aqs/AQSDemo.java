package cn.murphy.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
//
//        lock.lock();
//
//
//
//        lock.unlock();

        //带入一个银行办理业务的案例来模仿我们的aqs如何进行线程的管理和通知唤醒机制
        // 3个线程模拟3个银行网点，手里窗口办理业务的顾客

        //A 顾客就是第一个顾客，


        new Thread(() -> {
            lock.lock();

            System.out.println("A thread come in");

            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("A thread go out");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "线程A").start();


        //B 顾客就是第二个顾客，

        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("B thread come in");
                System.out.println("B thread go out");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "线程B").start();


        //C 顾客就是第三个顾客，

        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("C thread come in");
                System.out.println("C thread go out");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "线程B").start();

    }
}
