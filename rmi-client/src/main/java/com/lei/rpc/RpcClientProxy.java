package com.lei.rpc;

import java.lang.reflect.Proxy;

public class RpcClientProxy {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 1099;

    public static <T> T newProxy(final Class<T> clazz)
    {
        //这里为什么不能用clazz.getInterfaces();
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocateProcessorHandler(IP, PORT));
    }
}
