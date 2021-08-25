package cn.murphy.cas;

import java.util.concurrent.Semaphore;

/**
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程的控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(30,false); //模拟只有3个线程容量

        for (int i =1;i<=100;i++){ //模拟6个线程去抢
            new Thread(() -> {

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t线程抢占资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"\t线程释放资源");
                }


            }, ""+i).start();
        }


    }

}
