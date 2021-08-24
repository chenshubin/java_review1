package cn.murphy.enum1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile cas AtomicInteger
 */
public class ProdConsumer_BlockQueueDemo {


    public static void main(String[] args) {

    }
}

class MyResource{
    private volatile  boolean FLAG = true; //默认开启 进行生成+消费

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> queue = null;

    public  MyResource(BlockingQueue<String> queue){
        this.queue = queue;
        System.out.println(queue.getClass().getName());
    }

    public  void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = queue.offer(data,2, TimeUnit.SECONDS);

            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(5);
        }

        System.out.println(Thread.currentThread().getName()+"\t 程序暂停，表示flag=false，生产动作结束");
    }

    public  void myConsumer() throws InterruptedException {
//        String data = null;
        String retValue;
        while (FLAG){
//            data = atomicInteger.decrementAndGet()+"";
            retValue = queue.poll(2, TimeUnit.SECONDS);

            if(retValue != null){
                System.out.println(Thread.currentThread().getName()+"\t输出队列"+retValue+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t输出队列"+retValue+"失败");
            }
            TimeUnit.SECONDS.sleep(5);
        }

        System.out.println(Thread.currentThread().getName()+"\t 程序暂停，表示flag=false，生产动作结束");
    }
















}
