package cn.murphy.springlearn.cycledep;

public class ClientConstructor {


    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA(new ServiceB());
    }


}
