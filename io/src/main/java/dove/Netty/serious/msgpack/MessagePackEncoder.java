package dove.Netty.serious.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 编码器
 */
public class MessagePackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(msg);//对对象进行编码
        byteBuf.writeBytes(raw);
    }
}
