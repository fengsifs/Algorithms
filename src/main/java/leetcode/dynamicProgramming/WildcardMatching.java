package leetcode.dynamicProgramming;

/**
 * Created by FengSi on 2016/09/22 at 18:01.
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = p.length()-1; i >= 0; i--) {
            if (p.charAt(i) != '*')
                break;
            dp[s.length()][i] = true;
        }
        for (int i = s.length()-1; i >= 0; i--) {
            for (int j = p.length()-1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    dp[i][j] = dp[i+1][j+1];
                else if (p.charAt(j) == '*')
                    dp[i][j] = dp[i][j+1] || dp[i+1][j];
            }
        }
        return dp[0][0];
    }
}

/*
44. Wildcard Matching  QuestionEditorial Solution  My Submissions
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */