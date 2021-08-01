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

//        WorldTimer.setSchedule("T1", new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(1 + " 타이머");
//            }
//        }, 100, 700);

        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ServerCommand.main();
    }
}