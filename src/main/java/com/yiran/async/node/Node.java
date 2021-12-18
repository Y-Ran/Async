package com.yiran.async.node;

import com.yiran.async.handler.Handler;

import java.util.concurrent.ExecutorService;

public interface Node<OUT, IN> {

    ExecutorService getExecutorService();

    Handler<OUT, IN> getHandler();

    Node<?, OUT> getNextNode();

}
