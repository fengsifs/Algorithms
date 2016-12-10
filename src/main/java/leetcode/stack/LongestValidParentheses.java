package leetcode.stack;

import java.util.Stack;

/**
 * Created by FengSi on 2016/09/26 at 12:36.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == ')' &&
                    s.charAt(stack.peek()) == '(')
                stack.pop();
            else
                stack.push(i);
        }
        int res = 0;
        int end = s.length();
        int start = 0;
        while (!stack.isEmpty()) {
            start = stack.pop();
            res = Math.max(end - start - 1, res);
            end = start;
        }
        return Math.max(res, end);
    }
}

/*
32. Longest Valid Parentheses  QuestionEditorial Solution  My Submissions
Given a string containing just the characters '(' and ')', find the length
of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()",
which has length = 4.
 */