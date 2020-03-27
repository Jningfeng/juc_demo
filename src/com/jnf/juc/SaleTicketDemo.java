package com.jnf.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number = 30 ;
    private Lock lock = new ReentrantLock() ;
    public void saleTicket(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第: "+(number--)+"\t还剩第: "+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }
}

/**
 *  三个售票员卖出30张票
 *  在高内聚低耦合的前提下 线程  操作(对外暴露的调用方法)  资源类
 *
 *  //start的状态
 *  NEW(新建)  RUNNABLE(就绪)   BLOCKED(阻塞)   WAITING   TIMED_WAITING  TERMINATED
 */
public class SaleTicketDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
         //Lambda 接口仅有一个方法 拷贝小括号 写死右箭头 落地大括号(业务逻辑)
        //@FunctionalInterface(函数式接口)接口仅有一个方法不标注底层自动加入
        new Thread(() -> {for (int i = 1; i <= 40; i++) { ticket.saleTicket(); } }, "AAA").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) { ticket.saleTicket(); } }, "BBB").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) { ticket.saleTicket(); } }, "CCC").start();
    }
}