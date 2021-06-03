package rmi.client;

import rmi.service.ISerivce;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientDemo {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        ISerivce iSerivce = (ISerivce) Naming.lookup("rmi://127.0.0.1/Hello");
        System.out.println(iSerivce.sayHello("Tom "));
    }
}
