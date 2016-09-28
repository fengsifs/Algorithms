package exams.wap;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * Created by FengSi on 2016/09/26 at 20:58.
 */
public class WirelessRouters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++)
            s[i] = scanner.nextInt();
        int[][] adj = new int[n + 1][4];
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a][++adj[a][0]] = b;
            adj[b][++adj[b][0]] = a;
        }
//        System.out.println("Input: ");
//        int n = (int) (Math.random() * 29) + 2;
//        int m = (int) (Math.random() * (n < 100 ? n : 15)) + 1;
//        System.out.println(n + " " + m);
//        int[] s = new int[n + 1];
//        for (int i = 1; i <= n; i++)
//            s[i] = (int) (Math.random() * 10) + 1;
//        System.out.println(Arrays.stream(s).skip(1).mapToObj(Integer::toString).collect(joining(" ")));
//        int[][] adj = new int[n + 1][4];
//        for (int i = 1; i < n; i++) {
//            int a = (int) (Math.random() * n) + 1;
//            while (adj[a][0] == 3)
//                a = (int) (Math.random() * n) + 1;
//            int b = (int) (Math.random() * n) + 1;
//            while (b == a || adj[b][0] == 3 || (adj[a][0] >= 1 && adj[a][1] == b) ||
//                    (adj[a][0] >= 2 && adj[a][2] == b) || (adj[a][0] == 3 && adj[a][3] == b))
//                b = (int) (Math.random() * n) + 1;
//            System.out.print(a + " " + b + " ");
//            adj[a][++adj[a][0]] = b;
//            adj[b][++adj[b][0]] = a;
//        }
//        System.out.println();
        System.out.println(maxSat(s, adj, n, m));
    }

    private static int maxSat(int[] s, int[][] adj, int n, int m) {
        int res = 0;
        boolean[] wified = new boolean[n + 1];
        int[] rooms = new int[n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> rooms[b] - rooms[a]);
        int total = 0;
        for (int i = 1; i <= n; i++) {
            int sat = s[i];
            total += sat;
            for (int j = 1; j <= adj[i][0]; j++)
                sat += s[adj[i][j]];
            rooms[i] = sat;
            pq.add(i);
        }
        if (m > (n + 2) / 3)
            return total;
//        System.out.println();
//        System.out.println(Arrays.stream(rooms).skip(1).sorted().mapToObj(Integer::toString).collect(Collectors.joining(",")));
        while (m-- > 0 && !pq.isEmpty()) {
            int i = pq.poll();
            wified[i] = true;
            res += rooms[i];
            Set<Integer> set = new HashSet<>();
            for (int j = 1; j <= adj[i][0]; j++) {
                int k = adj[i][j];
                set.add(k);
                rooms[k] -= s[i] + s[k];
                for (int l = 1; l <= adj[k][0]; l++) {
                    int h = adj[k][l];
                    set.add(h);
                    rooms[h] -= s[k];
                }
                s[k] = 0;
            }
            s[i] = 0;
            set.stream().filter(r -> !wified[r]).forEach(r -> {
                pq.remove(r);
                if (rooms[r] > 0)
                    pq.offer(r);
                else
                    wified[r] = true;
            });
        }
//        System.out.println(Arrays.stream(rooms).skip(1).mapToObj(Integer::toString).collect(Collectors.joining(",")));
        return res;
    }
}
