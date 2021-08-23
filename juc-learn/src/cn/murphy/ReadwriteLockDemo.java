package cn.murphy;

import sun.awt.windows.ThemeReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadwriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for(int i=0;i<100;i++){
            int temp= i;
            new Thread(() -> {
                try {
                    myCache.put(String.valueOf(temp),String.valueOf(temp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            },"写线程"+i).start();

            new Thread(() -> {

                try {
                    myCache.get(String.valueOf(temp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "读线程"+i).start();




        }




    }

}


class MyCache{


    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public  void put(String key ,Object value) throws InterruptedException {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入:"+key);
            Thread.sleep(2);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成。");
        }finally {
            lock.writeLock().unlock();
        }



    }

    public  void get(String key) throws InterruptedException {
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在读取:"+key);
            Thread.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成。"+result);
        }finally{
            lock.readLock().unlock();
        }

    }





}