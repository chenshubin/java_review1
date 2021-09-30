package cn.murphy.springlearn.cycledep;

public class ClientSet {

    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();
        serviceA.setServiceB(serviceB);
        serviceB.setServiceA(serviceA);
    }

}
