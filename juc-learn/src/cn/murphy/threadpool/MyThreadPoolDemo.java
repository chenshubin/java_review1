package cn.murphy.threadpool;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        threadPoolInit();

        ExecutorService threadPool = new ThreadPoolExecutor(
        2, //开放2个窗口
        5,//设置最大窗口
        1L,//可以摸鱼的时间
        TimeUnit.SECONDS,//时间单位
        new LinkedBlockingDeque<>(3),//设置等候位置
        Executors.defaultThreadFactory() ,//默认的生成工厂
//        new ThreadPoolExecutor.AbortPolicy()//拒绝策略
//        new ThreadPoolExecutor.CallerRunsPolicy()// 调用者运行
//                new ThreadPoolExecutor.DiscardOldestPolicy()//丢弃等待最久的
        new ThreadPoolExecutor.DiscardPolicy()//满了就拒收
        );



        try{
            for(int i = 0 ; i<10; i++){
                int finalI = i;
                threadPool.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t"+ finalI +" 办理业务");


                });
            }
        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }




    }

    /**
     * 创建jdk自带的线程池
     */
    private static void threadPoolInit() {
        //        ThreadPoolExecutor
//        ScheduledExecutorService

        //Array arrays
        //Collection Collections
        //Excutor Excuteos


//        Executors.newFixedThreadPool(10);// 执行长期任务的固定池大小的
//        Executors.newSingleThreadExecutor();//一个任务一个线程
//        Executors.newCachedThreadPool();//使用执行很多


        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程

//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池一个处理线程

//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程 //系统自动决定


        //模拟100 个用户办理业务，每个用户就是一个来自外部的请求
        try{
            for(int i = 0 ; i<1000; i++){
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
