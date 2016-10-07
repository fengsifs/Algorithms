package leetcode.contests.weeklyContest_7;

/**
 * Created by FengSi on 2016/10/07 at 22:21.
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        long r = 0;
        long l = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i > l)
                l = i;
            r += i;
        }
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (valid(nums, m, mid))
                r = mid - 1;
            else
                l = mid + 1;
        }
        return (int) l;
    }

    private boolean valid(int[] nums, int m, long mid) {
        int count = 1;
        long temp = 0;
        for (int i : nums) {
            temp += i;
            if (temp > mid) {
                temp = i;
                count++;
                if (count > m)
                    return false;
            }
        }
        return true;
    }
}

/*
410. Split Array Largest Sum  QuestionEditorial Solution  My Submissions
Given an array which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays. Write an
algorithm to minimize the largest sum among these m subarrays.

Note:
Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.

Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */