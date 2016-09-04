package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by FengSi on 2016/08/28 at 9:22.
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.decodeString("ef"));
    }

    public String decodeString(String s) {
        if (s == null || s.length() == 0)
            return s;
        char[] cs = s.toCharArray();
        return decode(cs, 0, cs.length-1);
    }

    private String decode(char[] cs, int start, int end) {
        String res = "";
        if (cs[start] > '0' && cs[start] <= '9') {
            int k = start;
            int rep = 0;
            while (cs[k] != '[')
                rep = rep * 10 + (cs[k++] - '0');
            int count = 1;
            k++;
            int instart = k;
            while (k <= end) {
                if (cs[k] == '[')
                    count++;
                else if (cs[k] == ']')
                    count--;
                if (count == 0)
                    break;
                k++;
            }
            String s = decode(cs, instart, k - 1);
            while (rep-- > 0)
                res += s;
            if (k < end)
                res += decode(cs, k+1, end);
        }
        else {
            while (start <= end && cs[start] >= 'a' && cs[start] <= 'z') {
                res += cs[start];
                start++;
            }
            if (start < end)
                res += decode(cs, start, end);
        }
        return res;
    }

    public boolean validUtf8(int[] data) {
        int len = 0;
        for (int i : data) {
            int count = count(i);
            if ((len > 0 && count != 1) ||
                    (len == 0 && count == 1))
                return false;
            if (count == 1)
                len--;
            if (count > 1)
                len = count - 1;
        }
        return len == 0;
    }

    private int count(int d) {
        int[] c = new int[8];
        for (int i = 7; i >= 0; i--) {
            c[i] = d & 1;
            d >>= 1;
        }
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (c[i] == 0)
                break;
            count++;
        }
        return count;
    }

    public boolean isSubsequence(String s, String t) {
        if (s == null)
            return true;
        else if (t == null)
            return false;
        int l1 = s.length();
        int l2 = t.length();
        if (l1 == 0)
            return true;
        else if (l2 == 0)
            return false;
        int k = 0;
        for (int i = 0; i < l1; i++) {
            char c = s.charAt(i);
            while (k < l2 && t.charAt(k) != c)
                k++;
            if (k == l2)
                break;
            if (i == l1 - 1 && t.charAt(k) == c)
                return true;
            k++;
        }
        return false;
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
