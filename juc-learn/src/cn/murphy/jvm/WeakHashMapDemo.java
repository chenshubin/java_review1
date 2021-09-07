package cn.murphy.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("===================");
        myWeakHashMap();
    }

    private static void myHashMap(){
        Map<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());
    }


    private static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());
    }
}
