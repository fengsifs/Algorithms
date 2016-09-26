package leetcode.array;

/**
 * Created by FengSi on 2016/09/26 at 11:55.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int start = nums.length-1;
        while (start > 0 && nums[start] <= nums[start-1])
            start--;
        if (start == 0) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        for (int i = nums.length-1; i >= start; i--)
            if (nums[i] > nums[start-1]) {
                swap(nums, i, start-1);
                break;
            }
        reverse(nums, start, nums.length-1);
    }

    public void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    public void reverse(int[] nums, int s, int e) {
        for (int i = s, j = e; i < j; i++, j--)
            swap(nums, i, j);
    }
}

/*
31. Next Permutation  QuestionEditorial Solution  My Submissions
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */