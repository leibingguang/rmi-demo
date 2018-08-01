package com.lei.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIHelloService extends Remote{
    String sayHello(String msg) throws RemoteException;
}
