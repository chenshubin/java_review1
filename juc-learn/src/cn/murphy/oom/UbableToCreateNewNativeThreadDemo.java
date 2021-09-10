package cn.murphy.oom;

/**
 * 高并发请求服务器时，经常出现上面异常，准确的说该native thread 异常与对应的平台有关
 * 导致原因：
 * 1 应用创建太多线程了，一个应用进程创建多个线程，超过系统承载极限
 * 2 服务器不允许应用程序创建那么多线程，linux默认单个进程可以创建的线程数是1024个
 * 超过这个数就会报这个错
 *
 * 解决方案
 * 1 想办法降低创建应用的线程数量，分析应用是否真的需要创建那么多线程，如果不是，修改代码将线程改到最低。
 * 2 对于有的应用确实需要很多线程的，远超过迷人的1024限制，可以通过修改linux服务配置，扩大linux默认限制
 */
public class UbableToCreateNewNativeThreadDemo {
    public static void main(String[] args) {
        int i=0;
        while (true){
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI);
            }, "线程"+ ++i).start();
        }
    }
}
