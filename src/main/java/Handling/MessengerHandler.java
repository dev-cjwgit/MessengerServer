package Handling;

import Connector.Opcode.RecvOpcodePacket;
import DataBase.DAO;
import Packet.MessengerReadPacket;
import Packet.MessengerSendPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

import static Connector.Opcode.RecvOpcodePacket.*;


public class MessengerHandler {
    public static void PacketHandler(final RecvOpcodePacket opcode, ChannelHandlerContext ctx, MessengerReadPacket slea) throws Exception {
        switch (opcode) {
            case LOGIN_MESSENGER:
                String email = slea.readString();
                String password = slea.readString();
                if (DAO.GetAcount(email, password)) {
                    MessengerSendPacket sendPacket = new MessengerSendPacket();
                    sendPacket.writeShort(255);
                    sendPacket.writeShort(1);
                    sendPacket.writeString("로그인 축하해요^^");
                    ctx.writeAndFlush(sendPacket.getByteBuf());
                }
                break;
            case LOGOUT_MESSENGER:
                System.out.println("로그아웃입니다.");
                break;
            default:
                System.out.println("Unknown Opcode : " + opcode);
                break;
        }

    }
}
