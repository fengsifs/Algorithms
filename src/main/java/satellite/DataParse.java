package satellite;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.*;
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
            ResultSet set = statement.executeQuery("SELECT * FROM hj010c;");
            while (set.next()) {
                System.out.println(set.getString(3));
                System.out.println(set.getString(4));
            }
//            String path = "C:\\Users\\FengSi\\Desktop\\data";
//            File root = new File(path);
//            File[] fs = root.listFiles();
//            SAXReader reader = new SAXReader();
//            for (File f : fs) {
//                String tableName = f.getName().split("\\.")[0];
//                statement.execute("CREATE TABLE `hj010c` (\n" +
//                        "  `id` int(11) DEFAULT NULL,\n" +
//                        "  `code` varchar(15) DEFAULT NULL,\n" +
//                        "  `name` varchar(50) DEFAULT NULL,\n" +
//                        "  `type` varchar(10) DEFAULT NULL\n" +
//                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
//                List<Element> params = reader.read(f).getRootElement().elements();
//                for (Element param : params) {
//                    int id = Integer.parseInt(param.attributeValue("ID"));
//                    String code = param.attributeValue("Code");
//                    String name = param.attributeValue("Name");
//                    String type = param.attributeValue("Type");
//                    statement.execute("INSERT INTO " + tableName + "(id, code, name, type)" +
//                            "VALUE (" + id + ",\"" + code + "\",\"" + name + "\",\"" + type + "\");");
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
