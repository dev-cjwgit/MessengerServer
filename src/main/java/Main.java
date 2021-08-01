import Connector.Server.WorldTimer;
import Connector.ServerCommand;
import DataBase.DAO;
import Server.Netty.Server;

import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, blockingQueue);

        threadPoolExecutor.execute(new Server());

        WorldTimer.setSchedule("T1", new TimerTask() {
            @Override
            public void run() {
                System.out.println(1 + " 타이머");
            }
        }, 100, 700);
        WorldTimer.setSchedule("T2", new TimerTask() {
            @Override
            public void run() {
                System.out.println(2 + " 타이머");
            }
        }, 200, 700);
        WorldTimer.setSchedule("T3", new TimerTask() {
            @Override
            public void run() {
                System.out.println(3 + " 타이머");
            }
        }, 300, 700);
        WorldTimer.setSchedule("T4", new TimerTask() {
            @Override
            public void run() {
                System.out.println(4 + " 타이머");
            }
        }, 400, 700);
        WorldTimer.setSchedule("T5", new TimerTask() {
            @Override
            public void run() {
                System.out.println(5 + " 타이머");
            }
        }, 500, 700);


        try {
//            var res = DAO.executeQuery("SELECT * FROM account_friend;");
            DAO.deleteAccount(2);
        } catch (Exception sqlex) {
            sqlex.printStackTrace();
        }
        ServerCommand.main();
    }
}