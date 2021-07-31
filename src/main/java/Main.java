import DataBase.DAO;
import Server.Netty.Server;

import java.sql.SQLException;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, blockingQueue);

        threadPoolExecutor.execute(new Server());
        try {
//            var res = DAO.executeQuery("SELECT * FROM account_friend;");
            DAO.testFunc();
        } catch (Exception sqlex) {
            sqlex.printStackTrace();
        }

    }
}