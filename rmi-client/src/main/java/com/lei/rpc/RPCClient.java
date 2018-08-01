package com.lei.rpc;

public class RPCClient {
    public static void main(String[] args) {
        //获取IHelloService的代理对象
        IHelloService iHelloService = (IHelloService)RpcClientProxy.newProxy(IHelloService.class);
        //通过代理调用服务端获取结果
        System.out.println(iHelloService.sayHello("lei"));
    }

}
