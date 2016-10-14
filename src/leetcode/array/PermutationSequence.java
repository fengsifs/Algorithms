package leetcode.array;

/**
 * Created by FengSi on 2016/10/14 at 16:12.
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        int sum = 1;
        int c = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
            sum *= ++c;
        }
        sum /= c--;
        char[] cs = new char[n];
        k--;
        for (int i = 0; i < n-1; i ++) {
            cs[i] = get(k / sum, nums);
            k %= sum;
            sum /= c--;
        }
        cs[n-1] = (char) (nums[0] + '0');
        return new String(cs);
    }

    private char get(int k, int[] nums) {
        char c = (char) (nums[k] + '0');
        System.arraycopy(nums, k + 1, nums, k, nums.length - 1 - k);
        return c;
    }
}

/*
60. Permutation Sequence   QuestionEditorial Solution  My Submissions
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */