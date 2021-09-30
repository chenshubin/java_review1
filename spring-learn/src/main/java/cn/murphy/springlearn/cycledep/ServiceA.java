package cn.murphy.springlearn.cycledep;

import org.springframework.stereotype.Component;

@Component
public class ServiceA {

    private ServiceB  serviceB;

    public ServiceA(ServiceB serviceB){
        this.serviceB = serviceB;
    }

    public ServiceA() {
    }

    public ServiceB getServiceB() {
        return serviceB;
    }

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
        System.out.println("在A中设置了b");
    }
}
