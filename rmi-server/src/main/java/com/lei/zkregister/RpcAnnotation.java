package com.lei.zkregister;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {
    Class<?> value();
}
