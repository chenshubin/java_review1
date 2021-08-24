package cn.murphy.enum1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1 队列
 * 2 阻塞队列
 *  2.1阻塞队列有没有好的一面
 *  2.2 不得不阻塞 你如何管理
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        //设置大小，是否公平锁
        BlockingQueue<String > queue = new ArrayBlockingQueue<String>(3,false);

        //抛异常

        //        System.out.println(queue.add("a"));
//        System.out.println(queue.add("b"));
//        System.out.println(queue.add("c"));
//
//        System.out.println(queue.element());
//
//        //System.out.println(queue.add("d"));
//        System.out.println( queue.remove());
//        System.out.println( queue.remove());
//        System.out.println( queue.remove());
//
//        System.out.println(queue.element());


        //不报错
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("b"));
//        System.out.println(queue.offer("c"));
//        System.out.println(queue.offer("d"));
//        System.out.println(queue.offer("e"));
//
//        System.out.println(queue.peek());
//
//
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());


        //阻塞   消息队列的原理
//        queue.put("a");
//        queue.put("b");
//        queue.put("c");
//        queue.put("d");
//        queue.put("e");
//        System.out.println(queue.peek());
//
//        queue.take();
//        queue.take();
//        queue.take();
//        queue.take();

        //加入超时
        System.out.println(queue.offer("a",10L, TimeUnit.SECONDS));
        System.out.println(queue.offer("b",10L, TimeUnit.SECONDS));
        System.out.println(queue.offer("c",10L, TimeUnit.SECONDS));
        System.out.println(queue.offer("d",10L, TimeUnit.SECONDS));
        System.out.println(queue.peek());

        System.out.println(queue.poll(5,TimeUnit.SECONDS));
        System.out.println(queue.poll(5,TimeUnit.SECONDS));
        System.out.println(queue.poll(5,TimeUnit.SECONDS));
        System.out.println(queue.poll(5,TimeUnit.SECONDS));
        System.out.println(queue.poll(5,TimeUnit.SECONDS));
        System.out.println(queue.poll(5,TimeUnit.SECONDS));

    }




}
