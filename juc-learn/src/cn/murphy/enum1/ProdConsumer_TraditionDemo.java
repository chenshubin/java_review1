package cn.murphy.enum1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目 一个初始值为零的变量，两个线程对其交替操作，一个加 一个减1 来5轮
 *
 * 1 线程             操作              资源类
 * 2 判断             干活              通知
 * 3 防止虚假唤醒机制
 *
 *
 *
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for(int i=0;i<=5;i++){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "AA").start();

        new Thread(() -> {
            for(int i=0;i<=5;i++){
                try {
                    shareData.discrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "BB").start();


        new Thread(() -> {
            for(int i=0;i<=5;i++){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "CC").start();

        new Thread(() -> {
            for(int i=0;i<=5;i++){
                try {
                    shareData.discrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "DD").start();

        new Thread(() -> {
            for(int i=0;i<=5;i++){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "EE").start();

        new Thread(() -> {
            for(int i=0;i<=5;i++){
                try {
                    shareData.discrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "FF").start();




    }

}



class  ShareData{ //资源类

    private volatile int  number = 0;
    private Lock lock = new ReentrantLock();
    private Condition codition = lock.newCondition();

    public void increment() throws  Exception{

        lock.lock();
        try {
            //判断
            while ( number != 0){
                //等待，不能生产
                codition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            //通知唤醒
            codition.signalAll();
        }finally {
            lock.unlock();
        }
    }



    public void discrement() throws  Exception{

        lock.lock();
        try {
            //判断
            while ( number == 0){
                //等待，不能生产
                codition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            //通知唤醒
            codition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
