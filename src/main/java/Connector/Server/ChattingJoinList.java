package Connector.Server;

import DataBase.DAO;
import UserException.ResultHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChattingJoinList {
//    // chatting_uid, [account_uid, .,..] || DB Info
//    private static Map<Long, ArrayList<Integer>> list = new HashMap<>();

    // 온라인 유저들 chatting_uid, [ctx, ...] || Realtime Info
    private static Map<Long, ArrayList<ChannelHandlerContext>> online = new HashMap<>();

    //
    public static void loadDB() {
        var data = DAO.loadChattingUidInfo();
        if (data != null) {
            for (Map<String, String> item : data) {
                long chatting_uid = Long.parseLong(item.get("uid"));
                online.put(chatting_uid, new ArrayList<>());
            }
        }
    }


    //region online
    public static ResultHandler createChatting(long chatting_uid) {
        try {
            if (!online.containsKey(chatting_uid)) {
                online.put(chatting_uid, new ArrayList<>());
                return ResultHandler.Success;
            } else {
                return ResultHandler.Fail;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }

    public static ResultHandler deleteChatting(long chatting_uid) {
        try {
            if (online.containsKey(chatting_uid)) {
                online.remove(chatting_uid);
                return ResultHandler.Success;
            } else {
                return ResultHandler.Fail;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }

    public static ResultHandler enterChatting(long chatting_uid, ChannelHandlerContext ctx) {
        try {
            if (online.containsKey(chatting_uid)) {
                online.get(chatting_uid).add(ctx);
                return ResultHandler.Success;
            }
            return ResultHandler.Fail;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }

    public static ResultHandler exitChatting(long chatting_uid, ChannelHandlerContext ctx) {
        try {
            if (online.containsKey(chatting_uid)) {
                online.get(chatting_uid).remove(ctx);
                return ResultHandler.Success;
            }
            return ResultHandler.Fail;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }

    public static ArrayList<ChannelHandlerContext> getCtx(long chatting_uid) {
        if (online.containsKey(chatting_uid)) {
            return online.get(chatting_uid);
        }
        return null;
    }
    //endregion
}
