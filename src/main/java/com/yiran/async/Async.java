package com.yiran.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

public interface Async<T> {

    <R> Async<R> map(ExecutorService executor, Function<T, R> function) throws ExecutionException;

    Future<T> get() throws ExecutionException;

}
