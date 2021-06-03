package dove.Netty.serious.java;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 消息编码器
 */
public class JSONEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        //将返回的消息对象转为json字符串返回
        byte data [] = JSONObject.toJSONString(msg).getBytes() ;
        byteBuf.writeBytes(data);
    }
}
