package com.yiran.async;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class AsyncBuilderTest {

    @Test
    void open() throws ExecutionException, InterruptedException {
        ExecutorService one = Executors.newSingleThreadExecutor();
        ExecutorService two = Executors.newFixedThreadPool(5);
        int start = 10;
        long startTime = System.nanoTime();
        System.out.println("开始执行。。。 thread=" + Thread.currentThread().getId());
        Future<String> future = AsyncBuilder.open(start)
                .map(one, integer -> {
                    try {
                        long time = System.nanoTime() - startTime;
                        System.out.println("第一个map 开始执行。。。耗时=" + (time / 1000000) + ", thread=" + Thread.currentThread().getId());
                        Thread.sleep(1000L);
                        return integer * 10;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        long time = System.nanoTime() - startTime;
                        System.out.println("第一个map 执行完成。。。耗时=" + (time / 1000000) + ", thread=" + Thread.currentThread().getId());
                    }
                })
                .map(two, integer -> {
                    try {
                        long time = System.nanoTime() - startTime;
                        System.out.println("第二个map 开始执行。。。耗时=" + (time / 1000000) + ", thread=" +  Thread.currentThread().getId());
                        Thread.sleep(5000L);
                        return String.valueOf(integer);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        long time = System.nanoTime() - startTime;
                        System.out.println("第二个map 执行完成。。。耗时=" + (time / 1000000) + ", thread=" + Thread.currentThread().getId());
                    }
                })
                .start();
        long time = System.nanoTime() - startTime;
        System.out.println("准备获取结果。。。耗时=" + (time / 1000000) + ", thread=" + Thread.currentThread().getId());
        String out = future.get();
        time = System.nanoTime() - startTime;
        System.out.println("结果 = " + out + "。。。耗时=" + (time / 1000000) + ", thread=" + Thread.currentThread().getId());
    }


    @Test
    void open1() throws ExecutionException, InterruptedException {
        ExecutorService one = Executors.newSingleThreadExecutor();
        ExecutorService two = Executors.newFixedThreadPool(5);
        int start = 10;
        long startTime = System.currentTimeMillis();
        Async<String> async = null;
        System.out.println("开始执行。。。thread=" + Thread.currentThread().getId());
        for (int i = 0; i < 100000; i++) {
             async = AsyncBuilder.open(start)
                    .map(one, integer -> {
                        try {
                            System.out.println("第一个map 开始执行。。。thread=" + Thread.currentThread().getId());
                            Thread.sleep(1000L);
                            return integer * 10;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            System.out.println("第一个map 执行完成。。。thread=" + Thread.currentThread().getId());
                        }
                    })
                    .map(two, integer -> {
                        try {
                            System.out.println("第二个map 开始执行。。。thread=" +  Thread.currentThread().getId());
                            Thread.sleep(5000L);
                            return String.valueOf(integer);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            System.out.println("第二个map 执行完成。。。thread=" + Thread.currentThread().getId());
                        }
                    });
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("准备获取结果。。。耗时=" + time + "thread=" + Thread.currentThread().getId());
        String out = async.start().get();
        time = System.currentTimeMillis() - startTime;
        System.out.println("结果 = " + out + "。。。耗时=" + time + ", thread=" + Thread.currentThread().getId());

    }


}