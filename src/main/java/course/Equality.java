package course;

import java.util.Set;

/**
 * Created by FengSi on 2017/03/02 at 20:13.
 */
public class Equality {
    public static void main(String[] args) {
        System.out.println("Check series from 3^3 to 5^3 sum to 6^3: " + equality(3, 5, 6));
        System.out.println("Check series from 6^3 to 69^3 sum to 180^3: " + equality(6, 69, 180));
        find(10000);
    }

    // 判断从开始的数start到结束的数end的立方和是否等于目标数target的立方
    private static boolean equality(int start, int end, int target) {
        double right = Math.pow(target, 3);
        for (int i = start; i <= end; i++) {
            right -= Math.pow(i, 3);
            if (right < 0) {
                return false;
            }
        }
        return right == 0;
    }

    // 检查一个正数是否是某个整数的立方，如果是则返回该整数，否则返回-1，此处未考虑负数的情况
    private static int check(double number) {
        int test = (int) Math.pow(number, 1.0 / 3);
        if (Math.pow(test, 3) == number)
            return test;
        if (Math.pow(test + 1, 3) == number)
            return test + 1;
        return -1;
    }

    // 查找所有小于n的正整数范围内符合题目要求的连续正整数序列并打印输出结果
    private static void find(int n) {
        double[] record = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            record[i] = record[i - 1] + Math.pow(i, 3);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = check(record[j] - record[i - 1]);
                if (sum > 0) {
                    System.out.println("Find a series from " + i + "^3 to " + j + "^3 sum to " + sum + "^3");
                }
            }
        }
    }
}

/*
找到10000以内符合要求的结果为：
Find a series from 3^3 to 5^3 sum to 6^3
Find a series from 3^3 to 22^3 sum to 40^3
Find a series from 6^3 to 30^3 sum to 60^3
Find a series from 6^3 to 69^3 sum to 180^3
Find a series from 11^3 to 14^3 sum to 20^3
Find a series from 11^3 to 109^3 sum to 330^3
Find a series from 15^3 to 34^3 sum to 70^3
Find a series from 34^3 to 158^3 sum to 540^3
Find a series from 213^3 to 365^3 sum to 1581^3
Find a series from 213^3 to 555^3 sum to 2856^3
Find a series from 273^3 to 560^3 sum to 2856^3
Find a series from 291^3 to 339^3 sum to 1155^3
Find a series from 305^3 to 6895^3 sum to 82680^3
Find a series from 406^3 to 917^3 sum to 5544^3
Find a series from 556^3 to 654^3 sum to 2805^3
Find a series from 646^3 to 798^3 sum to 3876^3
Find a series from 1134^3 to 2133^3 sum to 16830^3
Find a series from 1735^3 to 3065^3 sum to 27060^3
Find a series from 3606^3 to 5802^3 sum to 62244^3
Find a series from 4966^3 to 7709^3 sum to 90090^3
 */