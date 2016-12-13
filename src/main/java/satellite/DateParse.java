package satellite;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

/**
 * Created by FengSi on 2016/12/13 at 13:50.
 */
public class DateParse {
    public static void main(String[] args) {
        try {
            String path = "D:\\Data\\HY0201";
            File f = new File(path);
            File[] fs = f.listFiles();
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://master:3306/satellite?useSSL=false";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            for (File file : fs) {
                int i = 0;
                String id = file.getName();
                ResultSet set = statement.executeQuery("SELECT * FROM hy0201 WHERE id=" + id);
                String code = null;
                if (set.next()) {
                    code = set.getString(2);
                }
                String des = "D:\\Data\\csv\\hy0201_" + code + ".csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(des)));
                BufferedReader reader;
                File[] fss = file.listFiles();
                for (File fs1 : fss) {
                    if (fs1.isDirectory()) {
                        for (File file1 : fs1.listFiles()) {
                            reader = new BufferedReader(new FileReader(file1));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                i++;
                                String[] s = line.split(",");
                                long time = getTime(Integer.parseInt(s[0]),
                                        Double.parseDouble(s[1]));
                                double value = Double.parseDouble(s[2]);
                                writer.write(time + "," + value);
                                writer.newLine();
                            }
                        }
                    }
                }
                writer.close();
                System.out.println(id + " " + code + " : " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static long getTime(int days, double seconds) {
        LocalDate date = LocalDate.of(1949, 12, 31).plusDays(days);
        LocalTime time = LocalTime.of(0, 0, 0, 0).plusNanos((long) (seconds * 1000000000));
        return Timestamp.valueOf(LocalDateTime.of(date, time)).getTime();
    }
}
