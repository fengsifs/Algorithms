package satellite;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by FengSi on 2016/12/13 at 9:42.
 */
public class DataParse {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://master:3306/satellite?useSSL=false";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            String path = args[0];
            File root = new File(path);
            File[] fs = root.listFiles();
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            BufferedReader reader;
            BufferedWriter writer;
            for (File f : fs) {
                String id = f.getName().split("\\.")[0];
                ResultSet set = statement.executeQuery("SELECT * FROM " + args[1] + " WHERE id=" + id);
                String code = null;
                if (set.next()) {
                     code = set.getString(2);
                }
                String tableName = args[1] + "_" + code;
                statement.execute("CREATE TABLE `" + tableName + "` (\n" +
                        "  `time` BIGINT DEFAULT NULL,\n" +
                        "  `value` DOUBLE DEFAULT NULL\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
                reader = new BufferedReader(new FileReader(f));
                writer = new BufferedWriter(new FileWriter(args[2] + tableName + ".csv"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] s = line.split(",");
                    long time = simpleDateFormat.parse(s[0]).getTime();
                    double value = Double.parseDouble(s[1]);
                    writer.write(time + "," + value);
                    writer.newLine();
                }
                reader.close();
                writer.close();
                set.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
