package com.yiran.async.node;

import com.yiran.async.handler.Handler;
import com.yiran.async.handler.MapHandler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

public class MapNode<T, R> extends AbstractNode<T> {

    private final Function<R, T> handler;

    private final Handler<R, T> mapHandler;

    public MapNode(ExecutorService executor, Function<R, T> function, Supplier<Future<R>> futureSupplier) {
        super();
        this.handler = function;
        this.mapHandler = new MapHandler<>(executor, futureSupplier);
    }

    @Override
    public Future<T> get() throws ExecutionException {
        return mapHandler.handle(this.handler);
    }
}
