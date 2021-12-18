package com.yiran.async.node;

import com.yiran.async.handler.PeekHandler;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class PeekNode<OUT> extends AbstractNode<OUT, OUT> {

    public PeekNode(ExecutorService executor, Consumer<OUT> consumer) {
        super(executor, new PeekHandler<>(consumer));
    }

}
