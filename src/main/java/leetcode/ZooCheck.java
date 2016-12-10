package leetcode;

import leetcode.array.PermutationSequence;
import leetcode.backTracking.GenerateParentheses;
import leetcode.backTracking.NQueensII;
import leetcode.contests.weeklyContest_6.ConvertaNumbertoHexadecimal;
import leetcode.contests.weeklyContest_6.TrappingRainWaterII;
import leetcode.twoPointers.MinimumWindowSubstring;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by FengSi on 2016/08/28 at 9:22.
 */
public class ZooCheck {
    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();
        System.out.println(permutationSequence.getPermutation(5, 10));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Map.Entry<Integer, Integer> a = map.lowerEntry(1);
    }


    public static boolean compare(int n) {
        int j = 1;
        while (n > 0)
            j *= n--;
        return Math.pow(2, Math.pow(n, 2)) > j;
    }
}
