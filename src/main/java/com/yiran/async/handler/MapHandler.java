package com.yiran.async.handler;

import java.util.function.Function;

public class MapHandler<OUT, IN> implements Handler<OUT, IN> {

    private final Function<IN, OUT> function;

    public MapHandler(Function<IN, OUT> function) {
        this.function = function;
    }

    @Override
    public OUT handle(IN in) {
        return this.function.apply(in);
    }
}
