package leetcode.contests.weeklyContest_7;

/**
 * Created by FengSi on 2016/10/07 at 22:08.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] chars = new int[128];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)]++;
            if (chars[s.charAt(i)] == 2) {
                res += 2;
                chars[s.charAt(i)] = 0;
            }
        }
        for (int i : chars) {
            if (i > 0) {
                res++;
                break;
            }
        }
        return res;
    }
}

/*
409. Longest Palindrome  QuestionEditorial Solution  My Submissions
Given a string which consists of lowercase or uppercase letters, find the length
of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

 */