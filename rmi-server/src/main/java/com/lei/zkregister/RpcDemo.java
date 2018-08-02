package com.lei.zkregister;

import com.lei.zkregister.service.HelloServiceImpl;
import com.lei.zkregister.service.IHelloService;

public class RpcDemo {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer("127.0.0.1:8080");
        rpcServer.bind(helloService);
        rpcServer.publish();
    }
}
