import java.util.Arrays;

/**
 * Created by FengSi on 2017/04/08 at 12:29.
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {6,10,6,9,7,8};
        System.out.println(solution.solution(A));
    }

    public int solution(int[] A) {
        int lenSmall = 1;
        int max = 1;
        Arrays.sort(A);
        int prevSmall = A[0];
        int i = 1;
        while (i < A.length) {
            if (A[i] == A[0]) {
                i++;
                lenSmall++;
            } else {
                break;
            }
        }
        max = lenSmall;
        if (i == A.length) {
            return A.length;
        }
        int prevBig = A[i];
        int lenBig = 1;
        for (int j = i + 1; j < A.length; j++) {
            if (A[j] == A[j - 1]) {
                lenBig++;
            } else {
                if (prevBig - prevSmall == 1) {
                    max = Math.max(max, lenBig + lenSmall);
                } else {
                    max = Math.max(Math.max(max, lenSmall), lenBig);
                }
                prevSmall = prevBig;
                lenSmall = lenBig;
                prevBig = A[j];
                lenBig = 1;
            }
        }
        return max;
    }
}
