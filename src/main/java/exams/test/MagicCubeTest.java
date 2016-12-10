package exams.test;

import java.util.Arrays;

import static exams.wap.MagicCube.location;
import static java.util.stream.Collectors.joining;

/**
 * Created by FengSi on 2016/09/29 at 10:15.
 */
public class MagicCubeTest {
    public static void main(String[] args) {
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
        System.out.println(Arrays.stream(big).mapToObj(Integer::toString).collect(joining(" ")));
        for (int[] small : smalls)
            System.out.println(small[small.length - 1] + " " +
                    Arrays.stream(small).mapToObj(Integer::toString).limit(small.length-1).collect(joining(" ")));
        double start = System.currentTimeMillis();
        System.out.println();
        System.out.println("Output:");
        int[][] loc = location(m, n, p, big, smalls);
        for (int[] ints : loc)
            System.out.println(Arrays.stream(ints).mapToObj(Integer::toString).collect(joining(" ")));
        System.out.println();
        double end = System.currentTimeMillis();
        System.out.println("耗时 " + (end-start)/1000 + " 秒");
        System.out.println(Arrays.stream(big).mapToObj(Integer::toString).collect(joining(" ")));
    }
}
