package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    public static String[] GetTables() throws SQLException {
        String[] temp;
        Connection conn = null;
        DatabaseConnection pool = new DatabaseConnection();
        try {
            conn = pool.getConnection();
            try (Statement statement = conn.createStatement())
            {
                ResultSet res = statement.executeQuery("show tables");
                int cnt=0;
                while (res.next()) {
                    String tblName = res.getString(1);
                    temp[cnt] = new String;
                    temp[cnt++] = tblName;
                }
            }
        }
        finally {
            if (conn != null) {
                pool.returnConnection(conn);
            }
        }
    }
}
