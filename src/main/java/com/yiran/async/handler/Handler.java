package com.yiran.async.handler;

import java.util.function.Function;

public interface Handler<OUT, IN> {

    OUT handle(IN in);

}
