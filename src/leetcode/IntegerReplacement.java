package leetcode;

/**
 * Created by FengSi on 2016/09/11 at 15:33.
 */
public class IntegerReplacement {
    public int integerReplacement(int n) {
        return replace(n);
    }

    private int replace(long n) {
        return n == 1 ? 0 : n % 2 == 0 ? replace(n / 2) + 1 : Math.min(replace(n + 1), replace(n - 1)) + 1;
    }
}

/*
397. Integer Replacement
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 */