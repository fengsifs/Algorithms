package leetcode.binarySearch;

/**
 * Created by FengSi on 2016/09/22 at 13:54.
 */
public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 < l2) return findMedianSortedArrays(nums2, nums1);
        if (l2 == 0) return (nums1[(l1 - 1) / 2] + nums1[l1 / 2]) / 2.0;
        int lo = 0;
        int hi = l2 * 2;
        while (lo <= hi) {
            int mid2 = (lo + hi) / 2;
            int mid1 = l1 + l2 - mid2;
            int L1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            int R1 = mid1 == l1 * 2 ? Integer.MAX_VALUE : nums1[mid1 / 2];
            int L2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
            int R2 = mid2 == l2 * 2 ? Integer.MAX_VALUE : nums2[mid2 / 2];
            if (L1 > R2) lo = mid2 + 1;
            else if (L2 > R1) hi = mid2 - 1;
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
        }
        return -1;
    }
}

/*
4. Median of Two Sorted Arrays  QuestionEditorial Solution  My Submissions
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */