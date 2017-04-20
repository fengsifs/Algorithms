import java.util.HashSet;
import java.util.Set;

/**
 * Created by FengSi on 2017/04/07 at 10:46.
 */
public class Test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a");
        System.out.println(stringBuilder.length());
        stringBuilder.append("");
        System.out.println(stringBuilder.length());
        stringBuilder.append("c");
        System.out.println(stringBuilder.length());
        System.out.println(stringBuilder.charAt(1));
        stringBuilder.replace(0, 1, "");
        System.out.println(stringBuilder);
        System.out.println(stringBuilder.length());
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
