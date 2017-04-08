/**
 * Created by FengSi on 2017/04/08 at 12:29.
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {2,2,2,2,1,2,-1,2,1,3};
        System.out.println(solution.solution(A));
    }

    public int solution(int[] A) {
        int max = 1;
        int len = 1;
        int start = 0;
        int prev = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1]) {
                len++;
                if (len > max) {
                    max = len;
                    start = prev;
                }
            } else {
                prev = i;
                len = 1;
            }
        }
        return start;
    }
}
