package com.lei.rmi;

import com.lei.service.RMIHelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        IRMIHelloService helloService = new RMIHelloServiceImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://127.0.0.1/rmiHello", helloService);
    }
}
