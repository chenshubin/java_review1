package cn.murphy;

/**
 * 传值或者传引用小case
 */
public class TestTransferValue {

//    Person person = new Person();

    public  void  changeValue1(int age){
        age = 30;
    }

    public  void changeValue2( Person person){
        person.setName("XXX");
    }

    public  void changeValue3( String str){
        str = "XXX";
    }






    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();

        int age = 20;
        test.changeValue1(age);
        System.out.println(age);

        Person person = new Person("ABC");
        test.changeValue2(person);
        System.out.println(person.getName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println(str);



    }
}


class Person{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }


    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
