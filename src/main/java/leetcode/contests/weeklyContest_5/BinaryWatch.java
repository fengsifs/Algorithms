package leetcode.contests.weeklyContest_5;

import java.util.*;

/**
 * Created by FengSi on 2016/09/18 at 18:54.
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        dfs(new int[10], 0, 0, list, num);
        return list;
    }

    private static void dfs(int[] a, int i, int k, List<String> list, int num) {
        if(k == num) {
            String res = getTime(a);
            if(res != null)
                list.add(res);
            return;
        }
        if(i == a.length) {
            return;
        }
        a[i] = 0;
        dfs(a, i+1, k, list, num);

        a[i] = 1;
        dfs(a, i+1, k+1, list, num);

        a[i] = 0;
    }

    private static String getTime(int[] a) {
        int hours = 0;
        for(int i = 0; i < 4; i++) {
            if(a[i] == 1) {
                hours = hours + (int)Math.pow(2, i);
            }
        }

        int minutes = 0;
        for(int i = 4; i < 10; i++) {
            if(a[i] == 1) {
                minutes = minutes + (int)Math.pow(2, i-4);
            }
        }
        String min = "" + minutes;
        if(minutes  <  10)
            min = "0" + min;
        String res = hours + ":" + min;
        if(hours  >= 12  ||  minutes  >=  60)
            return null;
        return res;
    }
}

/*
401. Binary Watch  QuestionEditorial Solution  My Submissions
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */