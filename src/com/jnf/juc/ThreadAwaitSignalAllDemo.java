package com.jnf.juc;

import com.sun.deploy.panel.SpecialTableRenderer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner2{

    private  int number = 0 ;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment()throws InterruptedException{
         //判断
            lock.lock();
            try {
                while (number != 0) {
                    condition.await();
                }
                //干活
                number++;
                System.out.println(Thread.currentThread().getName()+"\t"+number);
                //通知
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                 lock.unlock();
            }

        }
    public void decrement()throws InterruptedException{
        //判断
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

// lock ->  await ->signalAll
public class ThreadAwaitSignalAllDemo {
    public static void main(String[] args) {
         AirConditioner2 airConditioner2 = new AirConditioner2() ;
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                try {
                    airConditioner2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}
