package com.lei.zkregister;

import com.lei.rpc.RPCRequest;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class ProcessInvocator implements Runnable {
    private Map<String, Object> serviceMap;
    private Socket socket;

    public ProcessInvocator(Map<String, Object> serviceMap, Socket socket) {
        this.serviceMap = serviceMap;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RPCRequest request = (RPCRequest) inputStream.readObject();
            String response = invoke(request);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
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
     * 反射调用服务接口
     * @param rpcRequest
     * @return
     */
    private String invoke(RPCRequest rpcRequest) {
        String serviceName = rpcRequest.getClassName();
        String methodName = rpcRequest.getMethodName();
        Object[] params = rpcRequest.getParams();
        Object serviceObj = serviceMap.get(serviceName);
        String response = null;
        try {
            Method method = serviceObj.getClass().getMethod(methodName, String.class);
            response = (String) method.invoke(serviceObj, (String) params[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return response;

    }
}
