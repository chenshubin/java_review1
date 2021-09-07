package cn.murphy.jvm;

public class HelloGc {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("**********GC");

//        Thread.sleep(5000000);

        long totalMemory =  Runtime.getRuntime().totalMemory();
        long maxMemory =  Runtime.getRuntime().maxMemory();

        System.out.println("Total_Memory(-Xms) = "+totalMemory+"（字节）、"+totalMemory/1024/1024+"Mb");
        System.out.println("max_Memory(-Xmx) = "+maxMemory+"（字节）、"+maxMemory/1024/1024+"Mb");

        byte[] byArr = new byte[500*1024*1024];




    }
}
