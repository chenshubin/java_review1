package cn.murphy.oom;

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        while(true){
            ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        }
    }
}
