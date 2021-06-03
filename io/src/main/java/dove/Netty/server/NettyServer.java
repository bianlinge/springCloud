package dove.Netty.server;


import dove.HostInfo;
import dove.Netty.serious.java.JSONDecoder;
import dove.Netty.serious.java.JSONEncoder;
import dove.Netty.serious.msgpack.MessagePackDecoder;
import dove.Netty.serious.msgpack.MessagePackEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class NettyServer {
    //服务器端启动方法
    public void run() throws InterruptedException {
        // 线程池是提升服务器性能的重要技术手段，利用定长的线程池可以保证核心线程的有效数量
        // 在Netty之中线程池的实现分为两类：主线程池（接收客户端连接）、工作线程池（处理客户端连接）
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(10);//创建主线程接收线程池
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(20);//创建工作线程吃
        System.out.println("服务器启动成功，监听端口："+ HostInfo.PORT);
        try {
            // 创建一个服务器端的程序类进行NIO启动，同时可以设置Channel
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            // 接收到信息之后需要进行处理，于是定义子处理器
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 自定义长度解码器 解决TCP粘包黏包问题
                    //发送数据包长度 = 长度域的值 + lengthFieldOffset + lengthFieldLength + lengthAdjustment
                    //maxFrameLength - 发送的数据帧最大长度
                    //lengthFieldOffset - 定义长度域位于发送的字节数组中的下标
                    //lengthFieldLength - 用于描述定义的长度域的长度。
                    // lengthAdjustment - 满足公式: 发送的字节数组bytes.length - lengthFieldLength = bytes[lengthFieldOffset, lengthFieldOffset+lengthFieldLength] + lengthFieldOffset + lengthAdjustment
                    //initialBytesToStrip - 接收到的发送数据包，去除前initialBytesToStrip位
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,4));
//                    socketChannel.pipeline().addLast(new JSONDecoder());
                    socketChannel.pipeline().addLast(new MessagePackDecoder());
                    socketChannel.pipeline().addLast(new LengthFieldPrepender(4));
//                    socketChannel.pipeline().addLast(new JSONEncoder());
                    socketChannel.pipeline().addLast(new MessagePackEncoder());
                    socketChannel.pipeline().addLast(new EchoServerHandler());//追加业务处理器
                }
            });
            // 可以直接利用常量进行TCP协议的相关配置
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);//BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);//是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
            // ChannelFuture描述的时异步回调的处理操作
            ChannelFuture fature = serverBootstrap.bind(HostInfo.PORT).sync();
            fature.channel().closeFuture().sync();//等待Socket被关闭
        } finally {
            //关闭线程池
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
