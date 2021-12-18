package com.yiran.async.future;

import java.util.concurrent.Future;

public interface AsyncFuture<OUT> extends Future<OUT> {

    AsyncFuture<OUT> start();

}
