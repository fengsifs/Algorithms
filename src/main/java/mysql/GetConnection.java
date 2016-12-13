package mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FengSi on 2016/12/12 at 13:40.
 */
public class GetConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://master:3306/satellite?useSSL=false";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM test;");
            while (set.next()) {
                System.out.print(set.getInt(1) + "\t");
                System.out.println(set.getString(2));
            }
//            StringBuilder sb = new StringBuilder();
//            set = statement.executeQuery("SELECT * FROM s1087 WHERE time=1428371399912 OR time=1428402268305 OR time=1454202866612");
//            sb.append("[{s1087,[");
//            while (set.next()) {
//                String time = set.getString(1);
//                double value = set.getDouble(2);
//                int anomaly = set.getInt(3);
//                sb.append("[").append(time).append(",");
//                if (anomaly == 0)
//                    sb.append(value).append("],");
//                else
//                    sb.append("{").append(value).append(",{fillColor:'red',symbol:'diamond'}}],");
//            }
//            sb = sb.deleteCharAt(sb.length() - 1);
//            sb.append("]}]");
//            System.out.println(sb.toString());
            File file = new File("D:\\Data\\HJ010C\\anomalies\\12291.txt");
            System.out.println(file.getParentFile().getName());
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String tableName = "s" + file.getName().split("\\.")[0];
            statement.execute("CREATE TABLE " + tableName + " (time CHAR(20), value DOUBLE, anomaly INT);");
            BufferedReader bfr = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] s = line.split(",");
                String time = Long.toString(simpleDateFormat.parse(s[0]).getTime());
                double value = Double.parseDouble(s[1]);
                int anomaly = (int) Double.parseDouble(s[2]);
                statement.execute("INSERT INTO " + tableName + " VALUE (" +
                        time + "," + value + "," + anomaly + ");");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
