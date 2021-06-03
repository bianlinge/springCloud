package myrpc.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    //发布服务
    public void publish(Object service,int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);//启动服务监听
            System.out.println("启动服务,正在监听.....");
            Socket socket = serverSocket.accept();
            //持续监听客户端请求线程 一次请求就是一个进程
          /*  while (true) {
                executorService.execute(new ProcessorHandler(socket,service));
            }*/

            new ProcessorHandler(socket, service).run();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
