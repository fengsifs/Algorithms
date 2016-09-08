package leetcode;

/**
 * Created by FengSi on 2016/09/05 at 0:05.
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null)
            return true;
        else if (t == null)
            return false;
        int l1 = s.length();
        int l2 = t.length();
        if (l1 == 0)
            return true;
        else if (l2 == 0)
            return false;
        int k = 0;
        for (int i = 0; i < l1; i++) {
            char c = s.charAt(i);
            while (k < l2 && t.charAt(k) != c)
                k++;
            if (k == l2)
                break;
            if (i == l1 - 1 && t.charAt(k) == c)
                return true;
            k++;
        }
        return false;
    }
}

/*
392. Is Subsequence
User Accepted: 0
User Tried: 0
Total Accepted: 0
Total Submissions: 0
Difficulty: Medium
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially
a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting
some (can be none) of the characters without disturbing the relative positions of the remaining
characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.
 */