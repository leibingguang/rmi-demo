package com.lei.zkregister;

import com.lei.rpc.IHelloService;

import java.util.List;
import java.util.Random;

public class ClientDemo {
    public static void main(String[] args) {
        DiscoveryService discoveryService = new DiscoveryService();
        List<String> serviceAddresses = discoveryService.discoveryServices();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            String serviceAddress = serviceAddresses.get(random.nextInt(serviceAddresses.size()));
            IHelloService helloService = RpcClientProxy.newProxy(IHelloService.class, serviceAddress);
            System.out.println( serviceAddress + "------" + helloService.sayHello(String.valueOf(i)));
        }
    }
}
