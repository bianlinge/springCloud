package dove.NIO.client;

import dove.HostInfo;
import dove.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();// 打开客户端连接通道
        clientChannel.connect(new InetSocketAddress(HostInfo.HOST_IP, HostInfo.PORT));//连接
        ByteBuffer buffer = ByteBuffer.allocate(50);// 开辟缓冲区
        boolean flag = true;

        while (flag) {
            buffer.clear();//清空缓冲区
            String inputData = InputUtil.getString("请输入要发送的信息：").trim();
            buffer.put(inputData.getBytes());
            buffer.flip();// 重置缓冲区
            clientChannel.write(buffer);// 发送数据
            buffer.clear(); // 在读取之前进行缓冲区清空
            int readCount = clientChannel.read(buffer);
            buffer.flip() ;
            System.err.println(new String(buffer.array(),0,readCount));
            if("byebye".equalsIgnoreCase(inputData)) {
                flag = false ;
            }
        }
        clientChannel.close();
    }
}
