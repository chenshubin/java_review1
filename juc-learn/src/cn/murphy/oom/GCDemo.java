package cn.murphy.oom;

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {

        String str = "13213123";
        while (true){
            str += str + new Random().nextInt(10);
            str.intern();
        }

    }
}
