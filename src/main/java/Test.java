import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FengSi on 2017/04/07 at 10:46.
 */
public class Test {
    interface Person {
        void eat();
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/manageplatform?useUnicode=true&characterEncoding=UTF-8", "root", "mima");

        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM a");

    }

    private static int s() {
            int x = 1;
        try {
            return x;
        } finally {
            x++;
        }
    }
    private int ge() {
        int[] a = new int[2];
        try {
            a[0] = 1;
            return a[0];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a[0] = 2;
        }
        return 0;
    }
}
