package Server.Netty;

import Connector.Opcode.RecvOpcodePacket;
import Handling.MessengerHandler;
import Packet.MessengerReadPacket;
import Packet.RecvPacketManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.compression.ZlibEncoder;

@Sharable  //중요!
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    // 접속
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println(ctx.channel().remoteAddress().toString().split(":")[0] + " (이)가 접속함.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf slea) throws Exception {
//        byte[] bs = new byte[slea.writerIndex()];
//        slea.getBytes(0, bs);
//        MessengerReadPacket temp = new MessengerReadPacket(bs);
//        final int length = temp.readInt() + 4;
//        System.out.println(length);
//        if (length > 0) {
//            final int header = temp.readUShort();
//            for (final RecvOpcodePacket recv : RecvOpcodePacket.values()) {
//                if (recv.getValue() == header) {
//                    MessengerHandler.OpCodeHandler(recv, ctx, temp);
//                }
//            }
//        } else {
//            ctx.close();
//        }
        MessengerReadPacket packet = RecvPacketManager.getPacket(ctx, slea);
        if (packet != null) {
            final int header = packet.readUShort();
            for (final RecvOpcodePacket recv : RecvOpcodePacket.values()) {
                if (recv.getValue() == header) {
                    MessengerHandler.OpCodeHandler(recv, ctx, packet);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /* 모든 채널에서 패킷 읽기를 완료했을 때 */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    // 종료
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println(ctx.channel().remoteAddress().toString() + "님이 종료하셨습니다.");
    }
    // 종료 이벤트
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        super.channelUnregistered(ctx);
//    }

}