package leetcode;

import leetcode.backTracking.GenerateParentheses;
import leetcode.contests.weeklyContest_6.ConvertaNumbertoHexadecimal;
import leetcode.contests.weeklyContest_6.TrappingRainWaterII;
import leetcode.twoPointers.MinimumWindowSubstring;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by FengSi on 2016/08/28 at 9:22.
 */
public class ZooCheck {
    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("dsafafasdasdaaaaa", "aaaaa"));
    }

    public static boolean compare(int n) {
        int j = 1;
        while (n > 0)
            j *= n--;
        return Math.pow(2, Math.pow(n, 2)) > j;
    }
}
