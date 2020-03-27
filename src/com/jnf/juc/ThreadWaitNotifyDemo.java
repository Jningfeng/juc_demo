package com.jnf.juc;

class AirConditioner{  //资源类
    private int number = 0 ;
    public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0 ){
            this.wait();
        }
        //干活
        number++ ;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //判断
        while (number == 0 ){
            this.wait();
        }
        //干活
        number-- ;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }
}

/**
 *  现在两个线程，
 *  可以操作初始值为零的一个变量，
 *  实现一个线程对该变量加1，一个线程对该变量减1，
 *  交替，来10轮。
 *
 *  //高内聚低耦合前提下
 *    线程  操作  资源类
 *    判断  干活  通知
 *    标志位
 *    多线程交互中，必防止多线程的虚假唤醒 也即(判断只用while 不用if)
 *    As in the one argument version, interrupts and spurious wakeups are possible, and this method should always be used in a loop:
 *
 *    synchronized -> wait ->  notifyAll
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner  = new AirConditioner() ;
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
