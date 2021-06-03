package dove.Netty.server;

import dove.Member;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 处理Echo的操作方式，其中ChannelInboundHandlerAdapter是针对于数据输入的处理
 * Netty是基于NIO的一种开发框架的封装，这里面和AIO是没有任何关系的。
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println(msg.getClass() + " **************");
            Member member = (Member) msg;
            System.err.println("{服务器}" + member);//处理接受到的对象信息
            //业务处理写回客户端
            member.setName("【ECHO】" + member.getName());
            ctx.writeAndFlush(member);
        } finally {
            ReferenceCountUtil.release(msg);// 释放缓存
        }

    }

    //错误处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
