import DataBase.DAO;
import Server.Netty.Server;

import java.sql.SQLException;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100,100,10, TimeUnit.SECONDS, blockingQueue);

        threadPoolExecutor.execute(new Server());
        try {
            DAO.GetAcount();
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }

    }
}