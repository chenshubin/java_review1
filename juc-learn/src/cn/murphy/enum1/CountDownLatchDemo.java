package cn.murphy.enum1;

import cn.murphy.enum1.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计时，并且全部完成后，执行后面的事务
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
       // closeDoor();

       // playGame();

    }

    public static void playGame() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=1 ; i<=6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t国，被灭");
                countDownLatch.countDown();
            }, ""+ CountryEnum.forEach(i).getRetMsg()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t********秦帝国，一统中华");
    }


    public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i=1; i<=10; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
                countDownLatch.countDown();
            }, ""+i).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t********班长最后关门走人");
    }

}
