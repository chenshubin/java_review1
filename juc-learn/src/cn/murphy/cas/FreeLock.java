package cn.murphy.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 默认非公平锁
 */
public class FreeLock {

    volatile  int n = 0;

    public  void add(){
        n++;
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
    }


}
