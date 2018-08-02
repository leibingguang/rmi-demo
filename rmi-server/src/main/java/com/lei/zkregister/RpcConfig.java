package com.lei.zkregister;

public interface RpcConfig {

    /**
     * zk服务器连接串
     */
    String ZK_CONNECTION = "10.211.95.114:6830";

    /**
     * 注册服务的根地址
     */
    String SERVICE_ROOT= "/register";

    /**
     * 路径分隔符
     */
    String SPRIT_CHAR = "/";
}
