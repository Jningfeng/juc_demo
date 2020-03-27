package com.jnf.juc;

class Person{
    private Integer id ;
    private String personName ;

    public Person(String persionName) {
        this.personName = persionName;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersionName() {
        return personName;
    }

    public void setPersionName(String persionName) {
        this.personName = persionName;
    }
}
public class TestTransferValue {
    public  void changeValue1(int age){
        age = 30 ;
    }

    public  void changeValue2(Person person){
        person.setPersionName("xxx");
    }

    public  void changeValue3(String str){
        str ="xxx" ;
    }

    public static void main(String[] args) {
        TestTransferValue testTransferValue = new TestTransferValue() ;
        int age = 20 ;
        testTransferValue.changeValue1(age);
        System.out.println("age---"+age);

        Person person = new Person("abc");
        testTransferValue.changeValue2(person);
        System.out.println("personName------"+person.getPersionName());

        String str = "abc" ;
        testTransferValue.changeValue3(str);
        System.out.println("String-----"+str);


    }
}
