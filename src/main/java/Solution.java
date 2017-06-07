import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

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

    public static int resolve(String expr) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;
        while (j < expr.length()) {
            j++;
            if (j == expr.length() || expr.charAt(j) == ' ') {
                String s = expr.substring(i, j);
                if (s.equals("^")) {
                    if (stack.isEmpty()) {
                        return -1;
                    } else {
                        stack.push(stack.pop() + 1);
                    }
                } else if (s.equals("+")) {
                    if (stack.size() < 2) {
                        return -1;
                    } else {
                        int temp = stack.pop();
                        stack.push(temp + stack.pop());
                    }
                } else if (s.equals("*")) {
                    if (stack.size() < 2) {
                        return -1;
                    } else {
                        int temp = stack.pop();
                        stack.push(temp * stack.pop());
                    }
                } else {
                    int temp = Integer.parseInt(s);
                    if (stack.size() == 16) {
                        return -2;
                    } else {
                        stack.push(temp);
                    }
                }
                i = j + 1;
            }
        }
        return stack.peek();
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
