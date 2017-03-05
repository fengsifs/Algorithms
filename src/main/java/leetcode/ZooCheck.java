package leetcode;

import leetcode.array.PermutationSequence;
import leetcode.backTracking.GenerateParentheses;
import leetcode.backTracking.NQueensII;
import leetcode.binarySearch.Heaters;
import leetcode.classes.TreeNode;
import leetcode.contests.weeklyContest_6.ConvertaNumbertoHexadecimal;
import leetcode.contests.weeklyContest_6.TrappingRainWaterII;
import leetcode.tree.MostFrequentSubtreeSum;
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
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        MostFrequentSubtreeSum subtreeSum = new MostFrequentSubtreeSum();
        int[] re = subtreeSum.findFrequentTreeSum(root);
        for (int i : re) {
            System.out.println(i);
        }
    }


    public static boolean compare(int n) {
        int j = 1;
        while (n > 0)
            j *= n--;
        return Math.pow(2, Math.pow(n, 2)) > j;
    }
}
