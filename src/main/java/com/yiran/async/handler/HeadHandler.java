package com.yiran.async.handler;

public class HeadHandler<OUT> implements Handler<OUT, OUT>{

    private final OUT object;

    public HeadHandler(OUT object) {
        this.object = object;
    }

    @Override
    public OUT handle(OUT o) {
        return object;
    }
}
