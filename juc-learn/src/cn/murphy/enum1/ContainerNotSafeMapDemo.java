package cn.murphy.enum1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//java.util.ConcurrentModificationException
public class ContainerNotSafeMapDemo {
    public static void main(String[] args) {
        //Map<String,String> map = new HashMap<>();
        //使用 ConcurrentHashMap
        Map<String,String> map = new ConcurrentHashMap<>();
        //适应 Collections.synchronizedMap
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());

        for(int i = 0;i<100;i++){
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();

        }


    }
}
