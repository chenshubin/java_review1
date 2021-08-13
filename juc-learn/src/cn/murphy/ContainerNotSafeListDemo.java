package cn.murphy;

import sun.rmi.runtime.NewThreadAction;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java.util.ConcurrentModificationException
 */
public class ContainerNotSafeListDemo {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//修改方法1 使用Vector
//        List<String> list = new Vector<>();

//修改方法2  使用Collations的工具类  Collections.synchronizedList
//        List<String> list = Collections.synchronizedList(new ArrayList<String>());

//修改方法3 使用 juc里面的类    里面的就是加锁后的数组的copy
        List<String> list = new CopyOnWriteArrayList<String>();

//java.util.ConcurrentModificationException
//        for(Integer ingw : list){
//            if(1==ingw ){
//                list.add(2);
//            }
//        }

        for(int i=1;i<=100;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);


            }, String.valueOf(i)).start();
        }









    }
}
