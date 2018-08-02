package com.lei.zkregister;

import com.lei.rpc.IHelloService;
import com.lei.rpc.RpcConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.Random;

public class DiscoveryService {
    private CuratorFramework curatorFramework;

    /**
     * 服务地址
     */
    List<String> repos = null;

    public DiscoveryService() {
        this.curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString(RpcConfig.ZK_CONNECTION)
                .connectionTimeoutMs(60000)
                .retryPolicy(new RetryNTimes(5, 4000)).sessionTimeoutMs(60000).build();
        curatorFramework.start();
    }

    /**
     * 获取需要调用的服务地址
     *
     * @return
     */
    public List<String> discoveryServices() {

        String servicePath = RpcConfig.SERVICE_ROOT + RpcConfig.SPRIT_CHAR + IHelloService.class.getName();

        try {
            repos = curatorFramework.getChildren().forPath(servicePath);

            registerWatcher(servicePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return repos;
    }

    private void registerWatcher(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache
                (curatorFramework, path, true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher 异常" + e);
        }


    }
}
