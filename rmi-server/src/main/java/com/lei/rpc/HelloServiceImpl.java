package com.lei.rpc;

public class HelloServiceImpl implements IHelloService {
    public String sayHello(String msg) {
        return "hello, "+ msg;
    }
}
