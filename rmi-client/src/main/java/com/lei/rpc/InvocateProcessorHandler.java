package com.lei.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocateProcessorHandler implements InvocationHandler {
    /**
     * 服务器IP
     */
    private String ip;
    /**
     * 通信端口
     */
    private int port;

    public InvocateProcessorHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * 代理invoke方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();

        rpcRequest.setClassName(className);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setParams(args);
        Object response = TcpTransport.send(rpcRequest, ip, port);

        return response;
    }

}
