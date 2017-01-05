package satellite;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by FengSi on 2016/12/13 at 14:34.
 */
public class TransData {
    public static void main(String[] args) {
        try {
            String path = "D:\\Data\\good";
            File f = new File(path);
            File[] fs = f.listFiles();
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://master:3306/satellite?useSSL=false";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            for (File file : fs) {
                int i = 0;
                String id = file.getName();
                ResultSet set = statement.executeQuery("SELECT * FROM hy0201 WHERE id=" + id);
                String code = null;
                if (set.next()) {
                    code = set.getString(2);
                }
                String des = "D:\\Data\\csv1\\hy0201_" + code + ".csv";
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(des)));
                BufferedReader reader;
                File[] fss = file.listFiles();
                for (File fs1 : fss) {
                    if (!fs1.getName().contains("log")) {
                        reader = new BufferedReader(new FileReader(fs1));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            i++;
                            String[] s = line.split(",");
                            long time = simpleDateFormat.parse(s[0]).getTime();
                            double value = Double.parseDouble(s[1]);
                            writer.write(time + "," + value);
                            writer.newLine();
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
}
