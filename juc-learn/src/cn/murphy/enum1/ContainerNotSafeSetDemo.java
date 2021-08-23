package cn.murphy.enum1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

//java.util.ConcurrentModificationException
public class ContainerNotSafeSetDemo {
    public static void main(String[] args) {

//        Set<String> set = new  HashSet<String>();
        //使用集合工具类  Collections.synchronizedSet
//        Set<String> set = Collections.synchronizedSet(new  HashSet<String>());
        //使用juc工具 CopyOnWriteArraySet   实际是使用数组的copy和定位
        Set<String> set = new CopyOnWriteArraySet();


        for(int i =0;i<=100;i++){
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);



            }, String.valueOf(i)).start();
        }
    }
}
