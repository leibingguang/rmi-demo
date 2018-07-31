package com.lei.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hello_Steleton {

    public static final int PORT = 1099;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 创建serverSocket
     * @param port
     */
    public ServerSocket newServerSocket(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket  = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
    }

    public void publish(IHelloService service)
    {
        ServerSocket serverSocket = newServerSocket(PORT);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                InvocateProcessor invocateProcessor = new InvocateProcessor(service, socket);

                executorService.execute(invocateProcessor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
