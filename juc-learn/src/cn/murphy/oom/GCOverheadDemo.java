package cn.murphy.oom;

import java.util.ArrayList;
import java.util.List;

/**
 *  GC回收过长，而且GC的效果很差，消耗的资源还很多
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try{
            while(true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println(i);
            e.printStackTrace();
            throw e;
        }




    }
}
