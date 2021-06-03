package myrpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPTrasport {

    private String host;
    private int port;

    public TCPTrasport(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    //创建socket 客户端连接
    private Socket newSocket() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            System.out.println("发起连接host: "+host+":"+port);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return socket;
    }

    //与远程socket服务器连接通信
    public Object send(RpcRequest request) {
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Object object = null;
        try {
            socket = newSocket();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            object = objectInputStream.readObject();
            objectInputStream.close();
            objectOutputStream.close();
            return object;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发起远程服务异常");
        } finally {

            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return object;
    }
}
