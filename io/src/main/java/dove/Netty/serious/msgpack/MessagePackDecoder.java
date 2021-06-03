package dove.Netty.serious.msgpack;

import dove.Member;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

//MessagePack 解码器
public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        int len = byteBuf.readableBytes();
        byte data[] = new byte[len];
        byteBuf.getBytes(byteBuf.readerIndex(),data,0,len);
        MessagePack messagePack = new MessagePack();
        Member member = messagePack.read(data, messagePack.lookup(Member.class));
        list.add(member);
    }
}
