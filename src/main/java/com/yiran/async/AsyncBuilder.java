package com.yiran.async;

import com.yiran.async.node.HeadNode;

public class AsyncBuilder {

    public static <T> Async<T> open(T t) {
        return new HeadNode<>(t);
    }

}
