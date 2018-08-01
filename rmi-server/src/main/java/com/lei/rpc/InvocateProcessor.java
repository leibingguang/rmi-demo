package com.lei.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class InvocateProcessor implements Runnable {
    private Socket socket;
    private IHelloService service;

    public InvocateProcessor(IHelloService service, Socket socket) {
        this.socket = socket;
        this.service = service;
    }

    /**
     * 处理请求
     */
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RPCRequest rpcRequest = (RPCRequest) inputStream.readObject();
            Object response = invoke(rpcRequest);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反射调用方法
     * @param rpcRequest
     * @return
     */
    private Object invoke(RPCRequest rpcRequest) {
        Class<?> invocateClass = null;
        Method method = null;
        Object response = null;
        try {
            invocateClass = Class.forName(rpcRequest.getClassName());
            method = invocateClass.getDeclaredMethod(rpcRequest.getMethodName(), String.class);
            response = method.invoke(service.getClass().newInstance(), (String)rpcRequest.getParams()[0]);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }
}
