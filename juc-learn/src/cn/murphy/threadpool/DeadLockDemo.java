package cn.murphy.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * 死锁是指2个线程竞争2个以上资源的
 * 因争夺资源二造成的一种互相等待的现象，
 * 若无外力干涉那他们将无法推进下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA), "ThreadBBB").start();

        /**
         * jps -l
         * jstrack
         */



    }
}


class HoldLockThread implements  Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"尝试获取"+lockB);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"尝试获取"+lockA);
            }


        }
    }
}