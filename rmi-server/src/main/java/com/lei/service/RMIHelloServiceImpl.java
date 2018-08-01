package com.lei.service;

import com.lei.rmi.IRMIHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIHelloServiceImpl extends UnicastRemoteObject implements IRMIHelloService{
    public RMIHelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String msg) throws RemoteException{
        return "hello, " + msg;
    }
}
