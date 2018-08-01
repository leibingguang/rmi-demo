package com.lei.rpc;

import com.lei.service.HelloServiceImpl;

public class RPCServer {
    public static void main(String[] args) {
        Hello_Steleton hello_steleton = new Hello_Steleton();
        IHelloService service = new HelloServiceImpl();
        //发布服务
        hello_steleton.publish(service);
    }
}
