package cn.murphy.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可冲入锁
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
//        new Thread(() -> {
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "t1").start();
//
//        new Thread(() -> {
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "t2").start();


        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();


    }
}


class Phone implements  Runnable{


    public  synchronized  void  sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        Thread.sleep(1000);
        this.sendEmail();
    }

    public  synchronized  void  sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail()");
    }


    Lock lock = new ReentrantLock();
    public void run(){
        get();
    }


    public void get() {
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }


    public void set() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail()");
        }finally {
            lock.unlock();
        }

    }

}
