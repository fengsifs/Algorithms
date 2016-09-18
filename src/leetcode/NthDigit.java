package leetcode;

/**
 * Created by FengSi on 2016/09/18 at 18:51.
 */
public class NthDigit {
    public int findNthDigit(int n) {
        int count = 1;
        int nums = 9;
        long total = 9;
        int res = 1;
        while (n > total) {
            n -= total;
            count++;
            nums *= 10;
            total = count * nums;
            res *= 10;
            if (total < 0)
                break;
        }
        int temp = res + (n - 1) / count;
        int digit = n % count;
        if (digit == 0)
            return temp % 10;
        else {
            digit = count - digit;
            while (digit-- > 0)
                temp /= 10;
            return temp % 10;
        }
    }
}

/*
400. Nth Digit  QuestionEditorial Solution  My Submissions
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */