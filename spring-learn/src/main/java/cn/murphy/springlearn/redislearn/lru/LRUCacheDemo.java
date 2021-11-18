package cn.murphy.springlearn.redislearn.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo<K,V> extends LinkedHashMap<K,V> {

    private int capacity;//缓存数量

    public LRUCacheDemo(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    @Override
    protected  boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return

            super.size() > capacity;

               // super.removeEldestEntry(eldest);
    }




    public static void main(String[] args) {
        LRUCacheDemo cacheDemo = new LRUCacheDemo(3);

        cacheDemo.put(1,"a");
        cacheDemo.put(2,"b");
        cacheDemo.put(3,"c");
        System.out.println(cacheDemo.keySet());


        cacheDemo.put(4,"d");
        System.out.println(cacheDemo.keySet());

        cacheDemo.put(3,"c");
        System.out.println(cacheDemo.keySet());


        cacheDemo.put(3,"c");
        System.out.println(cacheDemo.keySet());

        cacheDemo.put(3,"c");
        System.out.println(cacheDemo.keySet());

    }
}
