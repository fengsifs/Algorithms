import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by FengSi on 2017/04/07 at 10:46.
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>();
        li.add(1);
        System.out.println(li.isEmpty());
        li.remove(0);
        System.out.println(li.isEmpty());
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
