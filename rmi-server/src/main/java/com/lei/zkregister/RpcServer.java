package com.lei.zkregister;


import com.lei.zkregister.service.IHelloService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {
    /**
     * 处理请求的线程池
     */
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    private String serviceAddress;
    private Object service;
    private Map<String, Object> serviceMap;

    public RpcServer(String serviceAddress) {
        this.serviceAddress = serviceAddress;
        serviceMap = new HashMap<>();
    }

    /**
     * 绑定服务
     * @param services
     */
    public void bind(Object ...services) {
        for(Object service: services)
        {
            Class<?> clazz = service.getClass().getAnnotation(RpcAnnotation.class).value();
            String serviceName = clazz.getName();
            serviceMap.put(serviceName, service);
        }

    }

    /**
     * 发布服务
     */
    public void publish()
    {
        int port = Integer.parseInt(serviceAddress.split(":")[1]);
        ServerSocket serverSocket = null;
        try {
            //注册服务
            for(String serviceName : serviceMap.keySet()) {
                IServiceRegister serviceRegister = new ServiceRegisterImpl(serviceAddress, serviceName);
                serviceRegister.register();
            }

            //对外发布服务
            serverSocket = new ServerSocket(port);
            while(true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessInvocator(serviceMap, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
