package exams.wap;

import java.util.*;

/**
 * Created by FengSi on 2016/09/26 at 20:58.
 */
public class WirelessRouters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++)
            s[i] = scanner.nextInt();
        int[][] adj = new int[n+1][4];
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a][++adj[a][0]] = b;
            adj[b][++adj[b][0]] = a;
        }
        WirelessRouters wirelessRouters = new WirelessRouters();
        int res = wirelessRouters.maxSat(s, adj, n, m);
        System.out.println(res);
        scanner.close();
    }

    private int maxSat(int[] s, int[][] adj, int n, int m) {
        int res = 0;
        Room[] rooms = new Room[n + 1];
        boolean[] wified = new boolean[n + 1];
        PriorityQueue<Room> pq = new PriorityQueue<>((a, b) -> b.getSat() - a.getSat());
        for (int i = 1; i <= n; i++) {
            int sat = s[i];
            for (int j = 1; j <= adj[i][0]; j++)
                sat += s[adj[i][j]];
            rooms[i] = new Room(i, sat);
            pq.add(rooms[i]);
        }
        while (m-- > 0 && !pq.isEmpty()) {
            int i = pq.poll().getId();
            wified[i] = true;
            res += rooms[i].getSat();
            Set<Integer> set = new HashSet<>();
            for (int j = 1; j <= adj[i][0]; j++) {
                int k = adj[i][j];
                set.add(k);
                rooms[k].setSat(rooms[k].getSat() - s[i]);
                rooms[k].setSat(rooms[k].getSat() - s[k]);
                for (int l = 1; l <= adj[k][0]; l++) {
                    int h = adj[k][l];
                    set.add(h);
                    rooms[h].setSat(rooms[h].getSat() - s[k]);
                }
            }
            set.stream().filter(integer -> !wified[integer]).forEach(integer -> {
                pq.remove(rooms[integer]);
                if (rooms[integer].getSat() > 0)
                    pq.offer(rooms[integer]);
                else
                    wified[integer] = true;
            });
        }
        return res;
    }

    private class Room {
        private int id;
        private int sat;

        public Room(int id, int sat) {
            this.id = id;
            this.sat = sat;
        }

        public void setSat(int sat) {
            this.sat = sat;
        }

        public int getSat() {
            return sat;
        }

        public int getId() {
            return id;
        }
    }
}
