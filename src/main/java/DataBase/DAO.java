package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DAO {
    public static ArrayList<Map<String, String>> executeQuery(String query) throws SQLException {
        ArrayList<Map<String, String>> result = null;
        Connection conn = null;
        DatabaseConnection pool = new DatabaseConnection();
        try {
            conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                ResultSet res = statement.executeQuery(query);
                res.last(); // 총 데이터 개수를 세기 위해서
                int row_count = res.getRow();  // 데이터의 개수
                if (row_count > 0) {  // 데이터가 존재하면
                    res.first();
                    res.previous(); // 다시 offset을 처음으로
                    result = new ArrayList<>();
                    int col_count = res.getMetaData().getColumnCount(); //  속성 개수를 알아옴
                    String[] col_name = new String[col_count]; // 속성 이름을 담을 공간
                    for (int i = 0; i < col_count; i++)
                        col_name[i] = res.getMetaData().getColumnName(i + 1); // 속성이름을 담아둠
                    while (res.next()) {
                        Map<String, String> dict = new HashMap<>();
                        for (int i = 0; i < col_count; i++)
                            dict.put(col_name[i], res.getString(i + 1)); // 속성이름과 벨류를 삽입

                        result.add(dict);
                    }
                }
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                pool.returnConnection(conn);
            }
        }
    }

    public static void GetAcount() throws SQLException {
        ArrayList<Map<String, String>> result = DAO.executeQuery("SELECT * FROM account;");
        if (result != null) {

        }
    }
}
