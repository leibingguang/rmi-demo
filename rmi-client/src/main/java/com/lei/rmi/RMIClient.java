package com.lei.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) {
        try {
            IRMIHelloService rmiHelloService = (IRMIHelloService)Naming.lookup("rmi://127.0.0.1/rmiHello");
            String response = rmiHelloService.sayHello("f");
            System.out.println(response);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
