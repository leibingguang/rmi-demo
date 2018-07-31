package com.lei.rpc;

public class ClientDemo {
    public static void main(String[] args) {
        IHelloService iHelloService = (IHelloService)RpcClientProxy.newProxy(IHelloService.class);

        System.out.println(iHelloService.sayHello("lei"));
    }

}
