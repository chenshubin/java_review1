package cn.murphy.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println("修改执行："+atomicInteger.compareAndSet(5,10)  + "，结果："+atomicInteger.get());
        System.out.println("修改执行："+atomicInteger.compareAndSet(5,11) +"，结果："+atomicInteger.get());


        // atomicInteger.getAndIncrement();
    }


}
