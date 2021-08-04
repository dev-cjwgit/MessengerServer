package Connector.Client;

import UserException.ResultHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

public class ConnectClient {

    //region client_info
    /*************************************
     *  ctx의 port로 uid, ctx를 찾을 수 있음  *
     *************************************/
    // port : uid
    public static HashMap<Integer, Integer> connect_client_port = new HashMap<>();
    // uid : ctx
    public static HashMap<Integer, ChannelHandlerContext> connect_client_uid = new HashMap<>();
    //endregion


    public static ChannelHandlerContext getClient(int uid) {
        if (connect_client_uid.containsKey(uid)) {
            return connect_client_uid.get(uid);
        }
        return null;
    }

    public static Integer getUid(ChannelHandlerContext ctx) {
        final int port = Integer.parseInt(ctx.channel().remoteAddress().toString().split(":")[1]);
        if (connect_client_port.containsKey(port)) {
            return connect_client_port.get(port);
        }
        return null;
    }

    public static ResultHandler logout(ChannelHandlerContext ctx) {
        try {
            final int port = Integer.parseInt(ctx.channel().remoteAddress().toString().split(":")[1]);
            if (ConnectClient.connect_client_port.containsKey(port)) {
                int uid = ConnectClient.connect_client_port.get(port);
                ConnectClient.connect_client_uid.remove(uid);
                ConnectClient.connect_client_port.remove(port);
                System.out.println(ctx.channel().remoteAddress().toString() + " 님이 로그아웃 하셨습니다"); // TODO: 콘솔알림
                return ResultHandler.Success;
            }
            return ResultHandler.Fail;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }
}