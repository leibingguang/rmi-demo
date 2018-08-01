package com.lei.service;

import com.lei.rpc.IHelloService;

public class HelloServiceImpl implements IHelloService {
    public String sayHello(String msg) {
        return "hello, "+ msg;
    }
}
