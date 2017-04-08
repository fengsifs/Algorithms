import java.util.Arrays;

/**
 * Created by FengSi on 2017/04/08 at 12:29.
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = new int[100000];
        System.out.println(solution.solution(A));
        int[] BA = {2, 3, 4, 2};
        System.out.println(solution.solution(BA));
    }

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        long pairs = 0;
        Arrays.sort(A);
        long count = 1;
        int prev = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] == prev) {
                count++;
            } else {
                if (count > 1) {
                    pairs += count * (count - 1) / 2;
                }
                count = 1;
                prev = A[i];
            }
        }
        if (count > 1) {
            pairs += count * (count - 1) / 2;
        }
        return (int) Math.min(pairs, 1000000000);
    }
}
