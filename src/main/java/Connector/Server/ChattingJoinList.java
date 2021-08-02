package Connector.Server;

import DataBase.DAO;
import UserException.ResultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChattingJoinList {
    // chatting_uid, [account_uid, .,..]
    private static Map<Long, ArrayList<Integer>> list = new HashMap<>();

    public static void loadDB() {
        list = new HashMap<>();
        var data = DAO.loadChattingJoinInfo();
        if (data != null) {
            for (Map<String, String> item : data) {
                long chatting_uid = Long.parseLong(item.get("chatting_uid"));
                int account_uid = Integer.parseInt(item.get("account_uid"));
                if (!list.containsKey(chatting_uid))
                    list.put(chatting_uid, new ArrayList<>());
                list.get(chatting_uid).add(account_uid);
            }
        }
    }

    public static ArrayList<Integer> getChattingUser(long chatting_uid) {
        try {
            if (list.containsKey(chatting_uid)) {
                return list.get(chatting_uid);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ResultHandler joinChatting(long chatting_uid, int account_uid) {
        try {
            if (!list.containsKey(chatting_uid))
                list.put(chatting_uid, new ArrayList<>());
            list.get(chatting_uid).add(account_uid);
            return ResultHandler.Success;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }

    public static ResultHandler exitChatting(long chatting_uid, int account_uid) {
        try {
            if (list.containsKey(chatting_uid)) {
                list.get(chatting_uid).remove(account_uid);
                return ResultHandler.Success;
            }
            return ResultHandler.Fail;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultHandler.Unknown_Exception;
        }
    }
}
