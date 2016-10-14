package leetcode.contests.weeklyContest_8;

import java.util.Arrays;

/**
 * Created by FengSi on 2016/10/09 at 10:53.
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) == 1)
            return false;
        sum >>= 1;
        boolean[] dp = new boolean[nums.length + 1];
        dp[0] = true;
        for (int n : nums) {
            for (int v = sum; v >= n; v--)
                dp[v] |= dp[v - n];
            if (dp[sum])
                return true;
        }
        return false;
    }
}

/*
416. Partition Equal Subset Sum My SubmissionsBack To Contest
Given a non-empty array containing only positive integers, find if the
array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */