package cn.murphy.threadpool;

import java.util.concurrent.*;

/**
 * 多线程中 ，第三种获取多线程的方法
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new MyThread1());
        t1.start();


        FutureTask<Integer> task1 = new FutureTask<Integer>(new MyThread2());
        Thread t2 = new Thread(task1,"BBB");
        t2.start();

        FutureTask<Integer> task2 = new FutureTask<Integer>(new MyThread3());
        Thread t3 = new Thread(task2,"CCC");
        t3.start();

        // ps多个线程抢一个 FutureTask 只进入一次

        System.out.println(task1.get()+task2.get());

    }

}


class MyThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"\t hello");
    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t hello callable1");
        TimeUnit.SECONDS.sleep(5);
        return 1024;
    }
}

class MyThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t hello callable2");
        TimeUnit.SECONDS.sleep(3);
        return 512;
    }
}

