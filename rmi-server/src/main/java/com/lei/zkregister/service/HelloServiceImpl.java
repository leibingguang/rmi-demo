package com.lei.zkregister.service;

import com.lei.zkregister.RpcAnnotation;

@RpcAnnotation(IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        return "Hello," + msg;
    }
}
