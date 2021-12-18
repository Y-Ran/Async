package com.yiran.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Async<T> {

    <R> Async<R> map(ExecutorService executorService, Function<T, R> function) throws ExecutionException;

    Async<T> peek(ExecutorService executorService, Consumer<T> consumer);

    Future<T> start() throws ExecutionException, InterruptedException;

}
