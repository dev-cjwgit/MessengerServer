package Handling;

import Connector.Client.ConnectClient;
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
                MessengerSendPacket sp = new MessengerSendPacket();
                sp.writeShort(255);
                if (DAO.getAcount(email, password)) {
                    final int uid = DAO.getUid(email);
                    final int port = Integer.parseInt(ctx.channel().remoteAddress().toString().split(":")[1]);
                    if (!ConnectClient.connect_client_uid.containsKey(uid)) {  // 서버에 접속중인 uid가 아니면
                        ConnectClient.connect_client_uid.put(uid, ctx);
                        ConnectClient.connect_client_port.put(port, uid);
                    } else {
                        sp.writeShort(-1);  // 이미 접속중인 계정
                    }
                } else {
                    sp.writeShort(0);
                }
                ctx.writeAndFlush(sp.getByteBuf());

                break;
            case LOGOUT_MESSENGER:
                final int port = Integer.parseInt(ctx.channel().remoteAddress().toString().split(":")[1]);
                if (ConnectClient.connect_client_port.containsKey(port)) {
                    int uid = ConnectClient.connect_client_port.get(port);
                    ConnectClient.connect_client_uid.remove(uid);
                    ConnectClient.connect_client_port.remove(port);
                }
                break;
            default:
                System.out.println("Unknown Opcode : " + opcode);
                break;
        }

    }
}
