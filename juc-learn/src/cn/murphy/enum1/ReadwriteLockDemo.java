package cn.murphy.enum1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发梁，读取共享资源应该可以同时进行
 * 但是，如果有一个线程想去写共享资源来，就不应该再有其他线程可以进行对该资源进行读和或写
 * 小总结：
 *  读-读能共存
 *  读-写不能共存
 *  写-写不能共存
 *
 */
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

    /**
     * 写锁
     * @param key
     * @param value
     * @throws InterruptedException
     */
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

    /**
     * 读锁
     * @param key
     * @throws InterruptedException
     */
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