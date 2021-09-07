package cn.murphy.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 软引用
 */
public class WeakReferenceDemo {

    /**
     * 当系统内存充足的时候不会被回收
     */
    public  static void weakRef_Memory_Enough(){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null ;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }


    public  static void weakRef_Memory_NotEnough(){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null ;
        System.gc();

        try {
            byte[] bytes = new byte[30 * 1024 *1024 ];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(weakReference.get());
        }


    }
    

    public static void main(String[] args) {
        weakRef_Memory_Enough();
        weakRef_Memory_NotEnough();
    }
}
