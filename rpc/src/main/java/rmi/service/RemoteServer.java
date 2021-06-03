package rmi.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        //已经发布远程服务 因为ISerivce集成了UnicastRemoteObject
        ISerivce iSerivce = new IServiceImpl();//

        //发布 RegistryImpl_stub服务
        LocateRegistry.createRegistry(1099);//必须1099

        Naming.rebind("rmi://127.0.0.1/Hello", (Remote) iSerivce);
        System.out.println("服务启动成功");
    }
}
