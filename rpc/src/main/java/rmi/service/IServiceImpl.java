package rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IServiceImpl  extends UnicastRemoteObject implements ISerivce{
    protected IServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
