package leetcode.contests.weeklyContest_6;

/**
 * Created by FengSi on 2016/09/25 at 10:08.
 */
public class ConvertaNumbertoHexadecimal {
    public String toHex(int num) {
        if (num == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        boolean p = num > 0;
        while (num != 0) {
            if (num % 2 == 0)
                sb.insert(0, 0);
            else
                sb.insert(0, 1);
            num /= 2;
        }
        if (p)
            return toH(sb.toString());
        else {
            String s = reverse(sb.toString());
            return toH(s);
        }
    }

    public String reverse(String s) {
        char[] cs = new char[32];
        int carry = 1;
        int len = s.length();
        for (int i = 31; i > 0; i--) {
            int j = len - 1 - (31 - i);
            char c = j >= 0 ? s.charAt(j) : '0';
            if (c == '0' && carry == 1)
                cs[i] = '0';
            else if (c == '1' && carry == 1) {
                cs[i] = '1';
                carry = 0;
            }
            else if (c == '0')
                cs[i] = '1';
            else
                cs[i] = '0';
        }
        cs[0] = '1';
        return new String(cs);
    }

    public String toH(String s) {
        int len = s.length();
        int l = len % 4 == 0 ? 4 : len % 4;
        int cl = l == 4 ? len / 4 : (len / 4 + 1);
        char[] res = new char[cl];
        res[0] = toD(s.substring(0, l));
        int k = 1;
        for (int i = l; i < s.length(); i += 4)
            res[k++] = toD(s.substring(i, i+4));
        return new String(res);
    }

    public Character toD(String s) {
        int res = 0;
        int d = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            res += (s.charAt(i) - '0') * d;
            d *= 2;
        }
        if (res < 10)
            return (char) ('0' + res);
        else
            return (char) ('a' + res - 10);
    }
}

/*
405. Convert a Number to Hexadecimal My SubmissionsBack To Contest
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

IMPORTANT:
You must not use any method provided by the library which converts/formats the number to hex directly. Such solution will result in disqualification of all your submissions to this problem. Users may report such solutions after the contest ends and we reserve the right of final decision and interpretation in the case of reported solutions.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
 */