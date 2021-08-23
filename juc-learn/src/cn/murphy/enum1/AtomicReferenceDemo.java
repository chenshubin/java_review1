package cn.murphy.enum1;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {



    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(100);

        AtomicReference<User> atomicReference = new AtomicReference<User>();

        User z3 = new User("z3",22);
        User li4 = new User("li4",25);
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());

    }

}

class User{
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
