package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by FengSi on 2016/08/28 at 9:22.
 */
public class Test {
    public static void main(String[] args) {
        int[][] rec = {{}};
        System.out.println(isRectangleCover(rec));
    }

    private static boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 1) {
            return true;
        } else {
            int xb = Integer.MAX_VALUE;
            int yb = Integer.MAX_VALUE;
            int xt = Integer.MIN_VALUE;
            int yt = Integer.MIN_VALUE;
            long m = 0;
            for (int[] rectangle : rectangles) {
                if (rectangle[0] < xb)
                    xb = rectangle[0];
                if (rectangle[1] < yb)
                    yb = rectangle[1];
                if (rectangle[2] > xt)
                    xt = rectangle[2];
                if (rectangle[3] > yt)
                    yt = rectangle[3];
                m += m(rectangle[0], rectangle[1], rectangle[2], rectangle[3]);
            }
            if (m != m(xb, yb, xt, yt)) {
                return false;
            } else {
                if (rectangles.length * rectangles.length < ((xt - xb) * (yt - yb))) {
                    for (int i = 0; i < rectangles.length; i++) {
                        for (int j = i + 1; j < rectangles.length; j++) {
                            if (overlap(rectangles[i], rectangles[j])) {
                                return false;
                            }
                        }
                    }
                } else {
                    boolean[][] cover = new boolean[xt-xb][yt-yb];
                    for (int[] rectangle : rectangles) {
                        for (int i = rectangle[0]; i < rectangle[2]; i++) {
                            for (int j = rectangle[1]; j < rectangle[3]; j++) {
                                if (cover[i-xb][j-yb])
                                    return false;
                                else
                                    cover[i-xb][j-yb] = true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean overlap(int[] rec1, int[] rec2) {
        int x1 = Math.max(rec1[0], rec2[0]);
        int y1 = Math.max(rec1[1], rec2[1]);
        int x2 = Math.min(rec1[2], rec2[2]);
        int y2 = Math.min(rec1[3], rec2[3]);
        return x2 > x1 && y2 > y1;
    }

    private static long m(int xb, int yb, int xt, int yt) {
        return (yt - yb) * (xt - xb);
    }

    private static int lf(int n) {
        if (n == 1)
            return n;
        if (n <= 3)
            return 2;
        return lb(n / 2) * 2;
    }

    private static  int lb(int n) {
        if (n == 1)
            return n;
        if (n <= 3)
            return n - 1;
        if (n % 2 == 0)
            return lf(n / 2) * 2 - 1;
        else
            return lf(n / 2) * 2;
    }

    private static char diff(String s, String t) {
        int[] letters = new int[26];
        char res = 0;
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] == -1) {
                res = (char) ('a'+i);
                break;
            }
        }
        return res;
    }
}
