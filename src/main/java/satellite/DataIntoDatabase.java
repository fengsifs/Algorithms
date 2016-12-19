package satellite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by FengSi on 2016/12/13 at 13:13.
 */
public class DataIntoDatabase {
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
                statement.execute("CREATE TABLE `" + tableName + "` (\n" +
                        "  `time` BIGINT DEFAULT NULL,\n" +
                        "  `value` DOUBLE DEFAULT NULL,\n" +
                        "  `anomaly` DOUBLE DEFAULT NULL\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
                String sql = "LOAD DATA LOCAL INFILE \"" + f.getAbsolutePath() + "\"\n" +
                        " INTO TABLE " + tableName + "\n" +
                        "FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' \n" +
                        "LINES TERMINATED BY '\\n';";
                statement.execute(sql);
                System.out.println("finish " + tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
