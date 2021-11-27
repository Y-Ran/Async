package com.yiran.async.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

public class MapHandler<T, R> implements Handler<T, R> {

    private final ExecutorService executorService;

    private final Supplier<Future<T>> futureSupplier;

    public MapHandler(ExecutorService executorService, Supplier<Future<T>> futureSupplier) {
        this.executorService = executorService;
        this.futureSupplier = futureSupplier;
    }

    @Override
    public Future<R> handle(Function<T, R> function) {
        return executorService.submit(() -> {
            T t = this.futureSupplier.get().get();
            return function.apply(t);
        });
    }
}
