package Connector.Client;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

public class ConnectClient {

    //region client_info
    /************************************
    *  ctx의 port로 uid, ctx를 찾을 수 있음  *
    ************************************/
    // port : uid
    public static HashMap<Integer, Integer> connect_client_port = new HashMap<>();
    // uid : ctx
    public static HashMap<Integer, ChannelHandlerContext> connect_client_uid = new HashMap<>();
    //endregion

}