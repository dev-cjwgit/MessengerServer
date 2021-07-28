package Handling;

import Connector.Opcode.RecvOpcodePacket;
import Packet.MessengerReadPacket;
import io.netty.channel.ChannelHandlerContext;

import static Connector.Opcode.RecvOpcodePacket.*;


public class MessengerHandler {
    public static void OpCodeHandler(final RecvOpcodePacket opcode, ChannelHandlerContext ctx, MessengerReadPacket slea) throws Exception {
        switch (opcode) {
            case LOGIN_MESSENGER:
                System.out.println("로그인입니다.");
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
