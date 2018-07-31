package com.lei.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class InvocateProcessorHandler implements InvocationHandler {
    private String ip;
    private int port;

    public InvocateProcessorHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Socket socket = new Socket(ip, port);
        RPCRequest rpcRequest = new RPCRequest();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        rpcRequest.setClassName(className);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setParams(args);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(rpcRequest);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        String response = (String)objectInputStream.readObject();
        objectOutputStream.close();
        objectInputStream.close();
        socket.close();
        return response;
    }

}
