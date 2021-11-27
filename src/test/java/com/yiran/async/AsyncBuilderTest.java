package com.yiran.async;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class AsyncBuilderTest {

    @Test
    void open() throws ExecutionException, InterruptedException {
        ExecutorService one = Executors.newSingleThreadExecutor();
        ExecutorService two = Executors.newFixedThreadPool(5);
        int start = 10;
        System.out.println("开始执行。。。thread=" + Thread.currentThread().getId());
        Future<String> future = AsyncBuilder.open(start)
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
                })
                .get();
        System.out.println("准备获取结果。。。thread=" + Thread.currentThread().getId());
        String out = future.get();
        System.out.println("结果 = " + out + "。。。thread=" + Thread.currentThread().getId());
    }


    @Test
    void open1() throws ExecutionException, InterruptedException {
        ExecutorService one = Executors.newSingleThreadExecutor();
        ExecutorService two = Executors.newFixedThreadPool(5);
        int start = 10;
        System.out.println("开始执行。。。thread=" + Thread.currentThread().getId());
        Async<String> async = AsyncBuilder.open(start)
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
        System.out.println("准备获取结果。。。thread=" + Thread.currentThread().getId());
        Thread.sleep(10000L);
        System.out.println("结果 = null。。。thread=" + Thread.currentThread().getId());


    }


}