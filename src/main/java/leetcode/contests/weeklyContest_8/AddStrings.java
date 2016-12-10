package leetcode.contests.weeklyContest_8;

/**
 * Created by FengSi on 2016/10/09 at 10:52.
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length())
            return addStrings(num2, num1);
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int len1 = num1.length();
        int len2 = num2.length();
        int k = len2 - 1;
        while (k >= 0) {
            char a = num1.charAt(len1 - len2 + k);
            char b = num2.charAt(k--);
            int x = (a - '0') + (b - '0') + carry;
            carry = x / 10;
            x %= 10;
            sb.insert(0, x);
        }
        while (len1 - len2 + k >= 0) {
            int x = num1.charAt(len1 - len2 + k--) - '0' + carry;
            carry = x / 10;
            x %= 10;
            sb.insert(0, x);
        }
        if (carry == 1)
            sb.insert(0, carry);
        return sb.toString();
    }
}

/*
415. Add Strings My SubmissionsBack To Contest
IMPORTANT:
Solutions which uses a BigInteger library or converting the input strings to another type (such as integer)
will result in disqualification of all submissions to this problem.
After the contest ends, users can view accepted submissions code and report cheating solutions.

Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */