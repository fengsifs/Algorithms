package interview;

import java.util.*;

/**
 * Created by FengSi on 2017/05/26 at 16:36.
 */
public class NSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int[] nums = new int[5];
            for (int i = 0; i < 5; i++) {
                nums[i] = scanner.nextInt();
            }
            int m = scanner.nextInt();
            System.out.println(check(nums, m));
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
