package com.lei.zkregister;

import com.lei.rpc.RpcConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

public class ServiceRegisterImpl implements IServiceRegister {
    private CuratorFramework curatorFramework;

    /**
     * 服务地址
     */
    private String serviceAddress;
    /**
     * 服务名
     */
    private String serviceName;

    public ServiceRegisterImpl(String serviceAddress, String serviceName) {
        this.serviceAddress = serviceAddress;
        this.serviceName = serviceName;

        curatorFramework = CuratorFrameworkFactory.builder().connectString(RpcConfig.ZK_CONNECTION)
                .retryPolicy(new RetryNTimes(5, 4000))
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(60000)
                .build();
        curatorFramework.start();
    }


    /**
     * 在zk上注册
     */
    @Override
    public void register() {
        String servicePath = RpcConfig.SERVICE_ROOT + RpcConfig.SPRIT_CHAR + serviceName ;
        try {
            //判断 /registrys/product-service是否存在，不存在则创建
            if(curatorFramework.checkExists().forPath(servicePath)==null){
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
            }
            String addressPath = servicePath + RpcConfig.SPRIT_CHAR + serviceAddress;
            String node = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());
            System.out.println(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
