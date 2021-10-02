package cn.murphy.springlearn.cycledep;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bank")
public class ClientConstructor {
    @Resource
    ServiceA serviceA;

    @GetMapping("helloworld")
    public String helloworld(){
        System.out.println(serviceA.toString());
            return "hello";
    }


    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA(new ServiceB());
    }


}
