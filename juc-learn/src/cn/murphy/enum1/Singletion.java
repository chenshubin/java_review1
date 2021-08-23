package cn.murphy.enum1;

public class Singletion {
    private  static volatile Singletion singletion = null;

    private Singletion(){
        System.out.println(Thread.currentThread().getName() +  "这个单例模式");
    }


    public static  Singletion getSingletion(){
        if(null == singletion){

            synchronized (Singletion.class){
                if(null == singletion){
                    singletion = new Singletion();
                }
            }
        }
        return singletion;
    }


    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            new Thread(()->{Singletion.getSingletion();},
                    String.valueOf(i)).start();
        }

    }
}
