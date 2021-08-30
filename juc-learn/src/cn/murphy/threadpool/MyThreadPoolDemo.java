package cn.murphy.threadpool;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

//        ThreadPoolExecutor
//        ScheduledExecutorService

        //Array arrays
        //Collection Collections
        //Excutor Excuteos


//        Executors.newFixedThreadPool(10);// 执行长期任务的固定池大小的
//        Executors.newSingleThreadExecutor();//一个任务一个线程
//        Executors.newCachedThreadPool();//使用执行很多

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程


//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池一个处理线程

        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程 //系统自动决定


        //模拟100 个用户办理业务，每个用户就是一个来自外部的请求
        try{
            for(int i = 0 ; i<100; i++){
                int finalI = i;
                threadPool.execute(()->{

                    System.out.println(Thread.currentThread().getName()+"\t"+ finalI +" 办理业务");


                });
            }



        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }


    }
}
