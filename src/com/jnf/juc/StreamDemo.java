package com.jnf.juc;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class User
{
    private Integer id;
    private String  userName;
    private int     age;

    public User() {
    }

    public User(Integer id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * 题目：请按照给出数据，找出同时满足
 *      偶数ID
 *      且年龄大于24
 *      且用户名转为大写
 *      且用户名字母倒排序
 *      最后只输出一个用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {

        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        //源头=>中间流水线=>结果
        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        }).filter(u -> {
            return u.getAge() > 24;
        }).map(u -> {
            return u.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);


     /*//4大函数式接口
        //函数型接口  一个参数，一个返回值
        Function<String,Integer> function = o -> {return o.length();};
        System.out.println(function.apply("abcd"));
        //断定型接口  一个参数，返回boolean
        Predicate<String> predicate = o -> { return  o.isEmpty();};
        System.out.println(predicate.test("aa"));
        //消费型接口，一个参数，没有返回值
        Consumer<String> consumer = o -> {System.out.println(o);};
        consumer.accept("java");
        //供给型接口，无参数，有返回值
        Supplier<String> supplier =() -> {return "java01";};
        System.out.println(supplier.get());*/

    }
}
