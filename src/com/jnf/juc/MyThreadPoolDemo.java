package com.jnf.juc;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //电脑核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //手写线程池
        ExecutorService executorService = new ThreadPoolExecutor(2,
                5,              //如何确定  电脑核数+1-2 ;
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        /**拒绝策略
         * ThreadPoolExecutor.AbortPolicy() 默认  直接抛出RejectedExecutionException异常阻止系统正常运行
         *
         *ThreadPoolExecutor.CallerRunsPolicy() 回退 调用者运行”一种调节机制，该策略既不会抛弃任务，也不
         *                                    会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
         *
         *ThreadPoolExecutor.DiscardOldestPolicy() 抛弃队列中等待最久的任务，然后把当前任务加人队列中
         *                                       尝试再次提交当前任务
         *
         * ThreadPoolExecutor.DiscardPolicy()    该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。
         *                                     如果允许任务丢失，这是最好的一种策略
         */
        try {
            for (int i = 1; i <= 10 ; i++) {executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 办理业务"); }); }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    public static void initPool() {
        //ExecutorService executorService = Executors.newFixedThreadPool(5); //一池5线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();//一池单线程
        ExecutorService executorService = Executors.newCachedThreadPool();//一池N线程
        try {
           for (int i = 1; i <= 10 ; i++) {executorService.execute(() -> {
               System.out.println(Thread.currentThread().getName() + "\t 办理业务"); }); }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
         executorService.shutdown();
        }
    }
}
