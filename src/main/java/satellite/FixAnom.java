package satellite;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengSi on 2016/12/16 at 10:28.
 */
public class FixAnom {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://master:3306/satellite?useSSL=false";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            String path = args[0];
            File root = new File(path);
            File[] fs = root.listFiles();
            for (File f : fs) {
                String tableName = f.getName().split("\\.")[0];
                String anomTable = tableName + "_anomalies_feature";
                List<String> time = new ArrayList<>();
                List<Double> value = new ArrayList<>();
                int i = 0;
                String sql = String.format("SELECT COUNT(*) totalCount FROM %s", anomTable);
                ResultSet set = statement.executeQuery(sql);
                int rows = 0;
                if (set.next()) rows = set.getInt("totalCount");
                sql = String.format("SELECT * FROM %s", tableName);
                set = statement.executeQuery(sql);
                while (set.next()) {
                    i++;
                    if (i > rows) {
                        time.add(set.getString(1));
                        value.add(set.getDouble(2));
                    }
                }
                for (int j = 0; j < time.size(); j++) {
                    String t = time.get(j);
                    String v = Double.toString(value.get(j));
                    sql = String.format("INSERT INTO %s VALUE ('%s', '%s', 0);", anomTable, t, v);
                    statement.execute(sql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
