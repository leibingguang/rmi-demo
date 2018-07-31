package com.lei.rpc;

public class ServerDemo {
    public static void main(String[] args) {
        Hello_Steleton hello_steleton = new Hello_Steleton();
        IHelloService service = new HelloServiceImpl();
        hello_steleton.publish(service);
    }
}
