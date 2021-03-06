package com.lei.zkregister;

import com.lei.rpc.IHelloService;
import com.lei.zkregister.service.HelloServiceImpl;

public class RpcDemo {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer("127.0.0.1:8080");
        rpcServer.bind(helloService);
        rpcServer.publish();
    }
}
