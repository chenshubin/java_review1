package cn.murphy.springlearn.cycledep;

import org.springframework.stereotype.Component;

@Component
public class ServiceB {
    private ServiceA  serviceA;

    public ServiceB(ServiceA serviceA){
        this.serviceA = serviceA;
    }

    public ServiceB() {
    }

    public ServiceA getServiceA() {
        return serviceA;
    }

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
        System.out.println("在B中设置了a");
    }
}
