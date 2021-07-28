package Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

class PacketInfo {
    public MessengerReadPacket slea;
    public int length;
}


// Socket 특성상 한번에 큰 크기의 데이터를 받아올 수 없으므로 매니저를 통하여 큰 데이터로 하나로 통합하여 받는 클래스.
public class RecvPacketManager {
    static private HashMap<Integer, PacketInfo> recvPacketManager = new HashMap<>();

    public static MessengerReadPacket getPacket(ChannelHandlerContext ctx, ByteBuf slea) {
        final int port = Integer.parseInt(ctx.channel().remoteAddress().toString().split(":")[1]);
        try {
            if (!recvPacketManager.containsKey(port)) {

                PacketInfo temp = new PacketInfo();
                byte[] bs = new byte[slea.writerIndex()];
                slea.getBytes(0, bs);
                temp.slea = new MessengerReadPacket(bs);
                temp.length = temp.slea.readInt() + 4;
                if (temp.length > temp.slea.getSize()) { // 데이터가 남아있는 경우
                    recvPacketManager.put(port, temp);
                } else if (temp.length < temp.slea.getSize()) {  // 데이터가 넘칠 경우
                    // 처리
                    throw new Exception("서버 처리중 오류가 발생하였습니다.");
                } else { // 데이터 크기가 딱 맞을경우
                    return temp.slea;
                }
            } else {
                PacketInfo temp = recvPacketManager.get(port);
                byte[] bs = new byte[slea.writerIndex()];
                temp.slea.append(bs);
                if (temp.length == temp.slea.getSize()) { // 같을경우
                    MessengerReadPacket tmp = temp.slea;
                    recvPacketManager.remove(port);
                    return tmp;
                } else if (temp.length < temp.slea.getSize()) {  // 데이터가 넘치는경우
                    throw new Exception("서버 처리중 오류가 발생하였습니다.");
                }
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
}
