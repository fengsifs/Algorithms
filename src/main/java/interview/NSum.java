package interview;

import java.util.*;

/**
 * Created by FengSi on 2017/05/26 at 16:36.
 */
public class NSum {
    private static List<Integer> list;
    private static int t;
    private static int mod;
    public static void add(int x) {
        int now = (x + t) % mod;
        list.add(now);
        for (int i = list.size() - 2; i >= 0; i--) {
            if (list.get(i) < now)
                list.set(i, now);
            else
                break;
        }
    }
    public static int query(int x) {
        return list.get(list.size() - x);
    }

    public static void main(String[] args){
        list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        mod = in.nextInt();
        while(m-- > 0){
            String ch = in.next();
            int x = in.nextInt();
            if(ch.equals("I")){
                add(x);
            } else {
                t = query(x);
                System.out.println(t);
            }
        }
    }
    public static boolean check(int[] nums, int m) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int num : nums) {
            Collection<Integer> collection = new ArrayList<>();
            for (Integer integer : set) {
                int sum = (num + integer) % m;
                if (sum == 0) {
                    return true;
                } else {
                    collection.add(sum);
                }
            }
            set.addAll(collection);
        }
        return false;
    }
}
