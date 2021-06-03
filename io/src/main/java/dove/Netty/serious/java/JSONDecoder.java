package dove.Netty.serious.java;


import com.alibaba.fastjson.JSON;
import dove.Member;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

//消息编码器
public class JSONDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
        int len = msg.readableBytes();// 可以用的数据长度
        byte[] data = new byte[len];
        msg.getBytes(msg.readerIndex(), data, 0, len);//将msg 写入data
        list.add(JSON.parseObject(new String(data)).toJavaObject(Member.class));//将接入的json转为相应的对象
    }
}
