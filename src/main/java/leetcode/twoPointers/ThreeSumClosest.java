package leetcode.twoPointers;

import java.util.Arrays;

/**
 * Created by FengSi on 2016/09/23 at 14:08.
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target)
                    return target;
                else if (sum > target)
                    end--;
                else
                    start++;
                if (Math.abs(sum - target) < Math.abs(res - target))
                    res = sum;
            }
        }
        return res;
    }
}

/*
16. 3Sum Closest  QuestionEditorial Solution  My Submissions
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */