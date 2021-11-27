package com.yiran.async.handler;

import java.util.concurrent.Future;
import java.util.function.Function;

public interface Handler<T, R> {

    Future<R> handle(Function<T, R> function);

}
