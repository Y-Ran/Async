package com.yiran.async.node;

import com.yiran.async.Async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractNode<T> implements Async<T> {

    protected AbstractNode<?> next;

    protected AbstractNode<?> head;

    public AbstractNode() {
    }

    @Override
    public <R> Async<R> map(ExecutorService executor, Function<T, R> function) throws ExecutionException {
        Supplier<Future<T>> supplier = () -> {
            try {
                return get();
            } catch (ExecutionException e) {
                throw new RuntimeException();
            }
        };
        AbstractNode<R> abstractNode = new MapNode<>(executor, function, supplier);
        abstractNode.head = this.head;
        this.next = abstractNode;
        return abstractNode;
    }
}
