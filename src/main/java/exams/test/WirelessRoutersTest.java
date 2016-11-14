package exams.test;

import java.util.Arrays;

import static exams.wap.WirelessRouters.maxSat;
import static java.util.stream.Collectors.joining;

/**
 * Created by FengSi on 2016/09/29 at 10:13.
 */
public class WirelessRoutersTest {
    public static void main(String[] args) {
        System.out.println("Input: ");
        int n = (int) (Math.random() * 29) + 2;
        int m = (int) (Math.random() * (n < 100 ? n : 15)) + 1;
        System.out.println(n + " " + m);
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++)
            s[i] = (int) (Math.random() * 10) + 1;
        System.out.println(Arrays.stream(s).skip(1).mapToObj(Integer::toString).collect(joining(" ")));
        int[][] adj = new int[n + 1][4];
        for (int i = 1; i < n; i++) {
            int a = (int) (Math.random() * n) + 1;
            while (adj[a][0] == 3)
                a = (int) (Math.random() * n) + 1;
            int b = (int) (Math.random() * n) + 1;
            while (b == a || adj[b][0] == 3 || (adj[a][0] >= 1 && adj[a][1] == b) ||
                    (adj[a][0] >= 2 && adj[a][2] == b) || (adj[a][0] == 3 && adj[a][3] == b))
                b = (int) (Math.random() * n) + 1;
            System.out.print(a + " " + b + " ");
            adj[a][++adj[a][0]] = b;
            adj[b][++adj[b][0]] = a;
        }
        System.out.println();
        System.out.println("Output:");
        System.out.println(maxSat(s, adj, n, m));
    }
}
