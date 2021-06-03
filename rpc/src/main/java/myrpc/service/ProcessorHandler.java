package myrpc.service;

/**
 * 为什么接受端用不同的对象 ？
 * 反序列化会失败不能进行强制转换 强制转换包含包类名
 * java.lang.ClassCastException: myrpc.client.RpcRequest cannot be cast to myrpc.service.RpcRequest
 * 	at myrpc.service.ProcessorHandler.run(ProcessorHandler.java:31)
 *
 * 	1.我使用了json 进行了转化
 * 	2.服务端的 myrpc.service.RpcRequest 打包给 client 端进行依赖
 * 	保持myrpc.service.RpcRequest一致
 */
/*import myrpc.client.RpcRequest;*/
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessorHandler() {
    }

    public ProcessorHandler(final Socket socket, final Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        try {
            //接收请求   read
            objectInputStream = new ObjectInputStream(socket.getInputStream());

           // RpcRequest request = (RpcRequest) objectInputStream.readObject();
            Object object = objectInputStream.readObject();
            JSON json = (JSON) JSONObject.toJSON(object);
            RpcRequest request = JSON.toJavaObject(json,RpcRequest.class);
            //处理请求返回客户端
            Object response = invoke(request);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //使用反射进行服务端请求处理
        Object[] args = request.getParameters();
        Class<?>[] argsTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsTypes[i] = args[i].getClass();
        }

        String methodName = request.getMethodName();
        Method method = service.getClass().getMethod(methodName, argsTypes);
        Object invokeRes = method.invoke(service, args);
        return invokeRes;
    }
}
