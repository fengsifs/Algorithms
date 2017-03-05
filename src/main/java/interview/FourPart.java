package interview;

/**
 * Created by FengSi on 2017/03/03 at 13:24.
 */
public class FourPart {
    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7};
        System.out.println(check(nums));
    }

    public static boolean check(int[] nums) {
        long[] sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        long total = sum[nums.length];
        for (int i = 1; i <= nums.length; i++) {
            long a = sum[i] - sum[0];
            if (a * 4 > total) {
                return false;
            }
            for (int j = i + 2; j <= nums.length; j++) {
                long b = sum[j] - sum[i + 1];
                if (b > a) {
                    break;
                } else if (b == a) {
                    for (int k = j + 2; k <= nums.length; k++) {
                        long c = sum[k] - sum[j + 1];
                        if (c > a) {
                            break;
                        } else if (c == a) {
                            if (k + 2 <= nums.length && sum[nums.length] - sum[k + 1] == a) {
                                System.out.println(a);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
