package com.yiran.async.node;

import com.yiran.async.Async;
import com.yiran.async.future.AsyncFuture;
import com.yiran.async.future.AsyncFutureImpl;
import com.yiran.async.handler.Handler;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractNode<OUT, IN> implements Async<OUT>, Node<OUT, IN> {

    protected Node<?, OUT> next;

    protected Node<?, ?> head;

    protected final ExecutorService executorService;

    protected final Handler<OUT, IN> handler;

    protected AbstractNode(ExecutorService executorService, Handler<OUT, IN> handler) {
        this.executorService = executorService;
        this.handler = handler;
    }

    @Override
    public <NEXT_OUT> Async<NEXT_OUT> map(ExecutorService executorService, Function<OUT, NEXT_OUT> function) {
        AbstractNode<NEXT_OUT, OUT> abstractNode = new MapNode<>(executorService, function);
        abstractNode.head = this.head;
        this.next = abstractNode;
        return abstractNode;
    }

    @Override
    public Async<OUT> peek(ExecutorService executorService, Consumer<OUT> consumer) {
        AbstractNode<OUT, OUT> abstractNode = new PeekNode<>(executorService, consumer);
        abstractNode.head = this.head;
        this.next = abstractNode;
        return abstractNode;
    }

    @Override
    public Future<OUT> start() {
        if (this == head) {
            return new Future<>() {
                @Override
                public boolean cancel(boolean mayInterruptIfRunning) {
                    return false;
                }

                @Override
                public boolean isCancelled() {
                    return false;
                }

                @Override
                public boolean isDone() {
                    return true;
                }

                @Override
                public OUT get() {
                    return handler.handle(null);
                }

                @Override
                public OUT get(long timeout, TimeUnit unit) {
                    return handler.handle(null);
                }
            };
        }
        AsyncFuture<OUT> future = new AsyncFutureImpl<>(head, this);
        future.start();
        return future;
    }

    @Override
    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    @Override
    public Handler<OUT, IN> getHandler() {
        return handler;
    }

    @Override
    public Node<?, OUT> getNextNode() {
        return next;
    }
}
