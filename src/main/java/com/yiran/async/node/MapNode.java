package com.yiran.async.node;

import com.yiran.async.handler.MapHandler;

import java.util.concurrent.ExecutorService;
import java.util.function.Function;

public class MapNode<OUT, IN> extends AbstractNode<OUT, IN> {

    public MapNode(ExecutorService executor, Function<IN, OUT> function) {
        super(executor, new MapHandler<>(function));
    }

}
