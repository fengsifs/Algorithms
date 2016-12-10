package leetcode.twoPointers;

/**
 * Created by FengSi on 2016/09/23 at 13:07.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            int cand = (right-left) * Math.min(height[left], height[right]);
            if (cand > max)
                max = cand;
            if (height[left] <= height[right]) {
                int l = height[left];
                while (height[left] <= l && left < right)
                    left++;
            }
            else {
                int r = height[right];
                while (height[right] <= r && right > left)
                    right--;
            }
        }
        return max;
    }
}

/*
11. Container With Most Water  QuestionEditorial Solution  My Submissions
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
 */