import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Create by Dove on 2019/9/17 23:01
 *
 * NIO 服务端
 */
public class NIO_Server {
    public static void main(String[] args) throws IOException {
        //创建了一个1024byte大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //创建选择器
        Selector selector = Selector.open();
        //创建通讯通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //通道设置为非阻塞方式
        ssc.configureBlocking(false);
        //通道绑定在到socket对象上
        ssc.socket().bind(new InetSocketAddress(8080));
        //把通道注册到选择器上
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            //检查已经注册到选择器上的额所有通道信道是否有需要的时间发生
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()){
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannal = (ServerSocketChannel) key.channel();//取得通信信道对象
                    SocketChannel sc = ssChannal.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ)== SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel)key.channel();//取得通信信道对象
                    while (true){
                        buffer.clear();//
                        int n = sc.read(buffer);//读取通信数据
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();//将缓冲区的字节而写入Channel通道
                    }
                    it.remove();
                }
            }
        }


    }
}
