package leetcode.twoPointers;

/**
 * Created by FengSi on 2016/09/23 at 13:08.
 */
public class TrappingRainWater {
    public int trap(int[] h) {
        int w = 0;
        int l = 0;
        int r = h.length - 1;
        while (l < r && h[l] == 0)
            l++;
        while (r > l && h[r] == 0)
            r--;
        int now = 0;
        while (l < r) {
            w -= now;
            int before = now;
            now = Math.min(h[l], h[r]);
            w += (now - before) * (r - l - 1);
            if (h[l] <= h[r]) {
                int ll = h[l++];
                while (h[l] <= ll && l < r)
                    w -= h[l++];
            }
            else {
                int rr = h[r--];
                while (h[r] <= rr && r > l)
                    w -= h[r--];
            }
        }
        return w;
    }
}

/*
42. Trapping Rain Water  QuestionEditorial Solution  My Submissions
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 */