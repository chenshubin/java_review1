package cn.murphy.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<String>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t put a");
                queue.put("aaa");
                System.out.println(Thread.currentThread().getName()+"\t put b");
                queue.put("bbb");
                System.out.println(Thread.currentThread().getName()+"\t put c");
                queue.put("ccc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Thread1").start();



        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t take a");
                queue.take();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t take b");
                queue.take();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t take c");
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Thread2").start();



    }


}
