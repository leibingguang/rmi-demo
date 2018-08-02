package com.lei.zkregister;

import java.lang.reflect.Proxy;

public class RpcClientProxy  {
    /**
     * 创建一个代理
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newProxy(Class<T> clazz, String addressService)
    {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocateProcessHandler(addressService));
    }
}
