package exams.wap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by FengSi on 2016/09/27 at 10:45.
 */
public class MagicCube {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int m = scanner.nextInt();
//        int n = scanner.nextInt();
//        int p = scanner.nextInt();
//        int[] big = new int[m * m * m];
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < m; j++)
//                for (int k = 0; k < m; k++)
//                    big[i * m * m + j * m + k] = scanner.nextInt();
//        int[][] smalls = new int[n][];
//        for (int c = 0; c < n; c++) {
//            int l = scanner.nextInt();
//            smalls[c] = new int[l * l * l + 1];
//            smalls[c][smalls[c].length - 1] = l;
//            for (int i = 0; i < l; i++)
//                for (int j = 0; j < l; j++)
//                    for (int k = 0; k < l; k++)
//                        smalls[c][i * l * l + j * l + k] = scanner.nextInt();
//        }
        int m = (int) (Math.random() * 6) + 2;
        int n = (int) (Math.random() * 12) + 1;
        int p = (int) (Math.random() * 3) + 3;
        int[] big = new int[m * m * m];
        int[][] smalls = new int[n][];
        for (int c = 0; c < n; c++) {
            int l = (int) (Math.random() * (m - 1)) + 1;
            smalls[c] = new int[l * l * l + 1];
            smalls[c][smalls[c].length - 1] = l;
            int x = (int) (Math.random() * (m - l)) + 1;
            int y = (int) (Math.random() * (m - l)) + 1;
            int z = (int) (Math.random() * (m - l)) + 1;
            for (int i = 0; i < l; i++)
                for (int j = 0; j < l; j++)
                    for (int k = 0; k < l; k++) {
                        smalls[c][i * l * l + j * l + k] = (int) (Math.random() * (p - 1)) + 1;
                        big[(i + x) * m * m + (j + y) * m + k + z] += p - smalls[c][i * l * l + j * l + k];
                    }
        }
        big = Arrays.stream(big).map(i -> i % p).toArray();
        System.out.println("Input:");
        System.out.println(m + " " + n + " " + p);
        System.out.println(Arrays.stream(big).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        for (int[] small : smalls)
            System.out.println(small[small.length - 1] + " " +
                    Arrays.stream(small).mapToObj(Integer::toString).limit(small.length-1).collect(Collectors.joining(" ")));
        double start = System.currentTimeMillis();
        MagicCube magicCube = new MagicCube();
        System.out.println();
        System.out.println("Output:");
        for (int[] ints : magicCube.location(m, n, p, big, smalls))
            System.out.println(Arrays.stream(ints).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        System.out.println();
        double end = System.currentTimeMillis();
        System.out.println("耗时 " + (end-start)/1000 + " 秒");
        System.out.println(Arrays.stream(big).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    private int[][] location(int m, int n, int p, int[] big, int[][] smalls) {
        int[][] loc = new int[n][3];
        dfs(m, n, p, big, smalls, 0, loc);
        return loc;
    }

    private void dfs(int m, int n, int p, int[] big, int[][] smalls, int level, int[][] loc) {
        int l = smalls[level][smalls[level].length - 1];
        for (int i = 0; i <= m - l; i++) {
            for (int j = 0; j <= m - l; j++) {
                for (int k = 0; k <= m - l; k++) {
                    loc[level][0] = i;
                    loc[level][1] = j;
                    loc[level][2] = k;
                    for (int o = 0; o < l; o++)
                        for (int q = 0; q < l; q++)
                            for (int r = 0; r < l; r++)
                                big[(i + o) * m * m + (j + q) * m + k + r] +=
                                        smalls[level][o * l * l + q * l + r];
                    if (level < n - 1)
                        dfs(m, n, p, big, smalls, level + 1, loc);
                    if (check(big, p))
                        return;
                    for (int o = 0; o < l; o++)
                        for (int q = 0; q < l; q++)
                            for (int r = 0; r < l; r++)
                                big[(i + o) * m * m + (j + q) * m + k + r] -=
                                        smalls[level][o * l * l + q * l + r];
                }
            }
        }
    }

    private boolean check(int[] big, int p) {
        for (int i : big) {
            if (i % p > 0)
                return false;
        }
        return true;
    }
}
