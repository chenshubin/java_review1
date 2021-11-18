package cn.murphy.springlearn.redislearn.lru;

import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;
import io.reactivex.internal.operators.maybe.MaybeOnErrorComplete;

import java.util.HashMap;
import java.util.Map;

//类似于aqs
public class LRUCacheDemo2 {

    //map负责查找，构建一个虚拟的双向链表，它里面安装的就是一个个node节点作为数据载体
    //  构造一个node节点作为数据载体
    class Node<K,V>{

        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node(){
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev  =this.next = null;
        }
    }

    //构建一个虚拟的双向链表，里面安装我们的node
    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        //构造方法
        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();

            head.next = tail ;
            tail.prev = head;
        }
        //添加到头
        public void addHead(Node<K,V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        //添加到尾部
        public void addTail(Node<K,V> node){
            node.next  = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
        }



        //删除节点
        public void removeNode(Node<K,V> node){
            node.next.prev = node.prev;
            node.prev.next = node.next;

            node.next = null;
            node.prev = null;

        }


        // 获取最后一个节点
        public Node getLast(){
            return tail.prev;
        }

        // 获取第一个节点
        public Node getFirst(){
            return head.next;
        }
    }


    private int cacheSize;
    Map<Integer,Node<Integer,Integer>> map;
    DoubleLinkedList<Integer,Integer> doubleLinkedList;


    public LRUCacheDemo2(int cacheSize) {
        this.cacheSize = cacheSize;//缓存坑位
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }


    public int get(int key){
        if(!map.containsKey(key)){
             return -1;
        }

        Node<Integer,Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addTail(node);
        return node.value;
    }


    public void put (int key,int value){
        if(map.containsKey(key)){
            Node<Integer,Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addTail(node);
        }else{
            if(map.size() >= cacheSize){
                Node first = doubleLinkedList.getFirst();
                map.remove(first.key);
                doubleLinkedList.removeNode(first);
            }

            //
            Node<Integer,Integer> node = new Node<>(key,value);
            map.put(key,node);
            doubleLinkedList.addTail(node);


        }
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
