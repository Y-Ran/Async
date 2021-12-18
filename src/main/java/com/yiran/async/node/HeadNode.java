package com.yiran.async.node;

import com.yiran.async.handler.HeadHandler;

public class HeadNode<OUT> extends AbstractNode<OUT, OUT> {

    private final OUT t;

    public HeadNode(OUT t) {
        super(null, new HeadHandler<>(t));
        this.t = t;
        head = this;
    }

}
