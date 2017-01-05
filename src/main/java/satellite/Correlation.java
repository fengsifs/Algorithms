package satellite;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by FengSi on 2016/12/10 at 11:48.
 */
public class Correlation {
    public static void main(String[] args) {
        try {
            String path = args[0];
            File[] fs = ReadFile.fs(path);
            double[][] points = ReadFile.points(args[0]);
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://master:3306/satellite?useSSL=false";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            for (int i = 0; i < fs.length; i++) {
                for (int j = i + 1; j < fs.length; j++) {
                    double rankA = new SpearmansCorrelation().correlation(points[i], points[j]);
                    if (rankA > 0.99)
                        rankA = 0.99;
                    if (rankA < 0.01)
                        rankA = 0.01;
//                    if (rankA > 0.6) {
                        String sensor1 = fs[i].getName().split("\\.")[0];
                        String sensor2 = fs[j].getName().split("\\.")[0];
                        String sql = String.format("INSERT INTO allcorrelation VALUES" +
                                "('%s','%s', %.2f),('%s','%s', %.2f);", sensor1, sensor2, rankA,
                                sensor2, sensor1, rankA);
                        statement.execute(sql);
                        System.out.println(sensor1 + "'s correlation with " + sensor2 +
                                " is A(apache) : " + rankA);
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
