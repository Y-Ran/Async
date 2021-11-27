package com.yiran.async;

import com.yiran.async.node.HeadNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncBuilder.class);

    public static <T> Async<T> open(T t) {
        return new HeadNode<>(t);
    }

}
