package leetcode.twoPointers;

/**
 * Created by FengSi on 2016/09/26 at 16:29.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] cs = new int[128];
        for (int i = 0; i < t.length(); i++)
            cs[t.charAt(i)]++;
        int start = 0, end = 0, counter = t.length(), p = 0, d = Integer.MAX_VALUE;
        while (end < s.length()) {
            if (cs[s.charAt(end++)]-- > 0) counter--;
            while (counter == 0) {
                if (end - start < d) {
                    d = end - start;
                    p = start;
                }
                if (cs[s.charAt(start++)]++ == 0) counter++;
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(p, p + d);
    }
}

/*
76. Minimum Window Substring  QuestionEditorial Solution  My Submissions
Given a string S and a string T, find the minimum window in S which will
contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one
unique minimum window in S.
 */