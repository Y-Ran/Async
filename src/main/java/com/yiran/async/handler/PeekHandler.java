package com.yiran.async.handler;

import java.util.function.Consumer;

public class PeekHandler<T> implements Handler<T, T> {

    private final Consumer<T> consumer;

    public PeekHandler(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public T handle(T t) {
        this.consumer.accept(t);
        return t;
    }
}
