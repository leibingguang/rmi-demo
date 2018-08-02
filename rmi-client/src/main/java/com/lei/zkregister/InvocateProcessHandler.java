package com.lei.zkregister;

import com.lei.rpc.RPCRequest;
import com.lei.rpc.TcpTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocateProcessHandler implements InvocationHandler {
    private String serviceAddress;

    public InvocateProcessHandler(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();

        rpcRequest.setClassName(className);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setParams(args);
        String[] arry = serviceAddress.split(":");
        String ip = arry[0];
        int port = Integer.parseInt(arry[1]);
        Object response = TcpTransport.send(rpcRequest, ip, port);

        return response;    }
}
