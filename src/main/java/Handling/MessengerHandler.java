package Handling;

import Connector.Client.ConnectClient;
import Connector.Opcode.RecvOpcodePacket;
import Connector.Opcode.SendOpcodePacket;
import DataBase.DAO;
import Packet.MessengerReadPacket;
import Packet.MessengerSendPacket;
import UserException.ResultHandler;
import io.netty.channel.ChannelHandlerContext;


public class MessengerHandler {
    public static void PacketHandler(final RecvOpcodePacket opcode, ChannelHandlerContext ctx, MessengerReadPacket slea) throws Exception {
        switch (opcode) {
            case LOGIN_MESSENGER: {
                String email = slea.readString();
                String password = slea.readString();
                MessengerSendPacket sp = new MessengerSendPacket();
                sp.writeShort(SendOpcodePacket.LOGIN_MESSENGER.getValue());
                if (DAO.canLogin(email, password) == ResultHandler.Success) {
                    final Integer uid = DAO.getUid(email);
                    final int port = Integer.parseInt(ctx.channel().remoteAddress().toString().split(":")[1]);
                    if (!ConnectClient.connect_client_uid.containsKey(uid)) {  // 서버에 접속중인 uid가 아니면
                        ConnectClient.connect_client_uid.put(uid, ctx);
                        ConnectClient.connect_client_port.put(port, uid);
                        System.out.println(ctx.channel().remoteAddress().toString() + "님이 " + email + " 로 로그인하였습니다."); // TODO: 콘솔알림
                        sp.writeShort(1);  // 접속 성공
                    } else {
                        sp.writeShort(-1);  // 이미 접속중인 계정
                    }
                } else {
                    sp.writeShort(0);
                }
                ctx.writeAndFlush(sp.getByteBuf());
                break;
            }

            case LOGOUT_MESSENGER: {
                MessengerSendPacket sp = new MessengerSendPacket();
                sp.writeShort(SendOpcodePacket.LOGOUT_MESSENGER.getValue());
                if (ConnectClient.logout(ctx) == ResultHandler.Success) {
                    System.out.println(ctx.channel().remoteAddress().toString() + "님이 로그아웃하셨습니다.");
                    sp.writeShort(1);
                } else {
                    sp.writeShort(-1);
                }
                ctx.writeAndFlush(sp.getByteBuf());
                break;
            }

            case INSERT_FIREND: {
                int my_uid = slea.readInt();
                int friend_uid = slea.readInt();

                MessengerSendPacket sp = new MessengerSendPacket();
                sp.writeShort(SendOpcodePacket.INSERT_FIREND.getValue());
                if (DAO.insertFriend(my_uid, friend_uid) == ResultHandler.Success) {
                    sp.writeShort(1);
                } else {
                    sp.writeShort(0);
                }
            }

            case INSERT_CHATTING: {
                int my_uid = slea.readInt();
                String title = slea.readString();
                MessengerSendPacket sp = new MessengerSendPacket();
                sp.writeShort(SendOpcodePacket.INSERT_CHATTING.getValue());
                if (DAO.insertChatting(my_uid, title) == ResultHandler.Success) {
                    sp.writeShort(1);
                } else {
                    sp.writeShort(0);
                }
                ctx.writeAndFlush(sp.getByteBuf());
            }

            default: {
                System.out.println("Unknown Opcode : " + opcode);
                break;
            }
        }

    }
}
