package Connector.Server;

import UserException.ResultHandler;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class WorldTimer {
    private static HashMap<String, Timer> scheduler = new HashMap<>();

    public static void setSchedule(String key, TimerTask tk, int delay, int period) throws Exception {
        if (scheduler.containsKey(key))
            throw new Exception("이미 존재하는 키입니다.");
        try {
            Timer timer = new Timer();
            timer.schedule(tk, delay, period);
            scheduler.put(key, timer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void stopSchedule(String key) throws Exception {
        if (scheduler.containsKey(key)) {
            scheduler.get(key).cancel();
            scheduler.remove(key);
            return;
        }
        throw new Exception("존재하지 않는 타이머입니다.");
    }
}
