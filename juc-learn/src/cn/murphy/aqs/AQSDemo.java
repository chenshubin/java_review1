package cn.murphy.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();



        lock.unlock();



    }
}
