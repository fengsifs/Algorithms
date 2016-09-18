package leetcode;

/**
 * Created by FengSi on 2016/09/18 at 18:53.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        while (k > 0) {
            if (k >= num.length())
                return "0";
            else {
                if (num.charAt(1) == '0') {
                    int j = 2;
                    while (j < num.length() && num.charAt(j) == '0')
                        j++;
                    if (j == num.length())
                        return "0";
                    else {
                        num = num.substring(j, num.length());
                        k--;
                    }
                }
                else {
                    int tt = 0;
                    while (tt < num.length() - 1 && num.charAt(tt+1) >= num.charAt(tt))
                        tt++;
                    num = num.substring(0, tt) + (tt < num.length()-1 ? num.substring(tt+1, num.length()) : "");
                    k--;
                }
            }
        }
        return num;
    }
}

/*
402. Remove K Digits  QuestionEditorial Solution  My Submissions
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 105 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */