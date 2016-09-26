package exams.wap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by FengSi on 2016/09/26 at 20:58.
 */
public class WirelessRouters {
    static class Room {
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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++)
            s[i] = scanner.nextInt();
        int[][] adj = new int[n+1][4];
        for (int i = 1; i <= n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adj[a][++adj[a][0]] = b;
            adj[b][++adj[b][0]] = a;
        }
        int res = 0;
        Room[] rooms = new Room[n + 1];
        PriorityQueue<Room> pq = new PriorityQueue<>((a, b) -> b.getSat() - a.getSat());
        for (int i = 1; i <= n; i++) {
            int sat = 0;
            for (int j = 1; j <= adj[i][0]; j++)
                sat += adj[i][j];
            rooms[i] = new Room(i, sat);
            pq.add(rooms[i]);
        }
        while (m-- > 0) {
            int i = pq.poll().getId();
            res += rooms[i].getSat();
            rooms[i].setSat(rooms[i].getSat() - s[i]);
            Set<Integer> set = new HashSet<>();
            for (int j = 1; j <= adj[i][0]; j++) {
                int k = adj[i][j];
                set.add(k);
                rooms[k].setSat(rooms[k].getSat() - s[k]);
                for (int l = 1; l <= adj[k][0]; l++) {
                    int h = adj[k][l];
                    set.add(h);
                    rooms[h].setSat(rooms[h].getSat() - s[k]);
                }
            }
            set.stream().filter(integer -> integer != i).forEach(integer -> {
                pq.remove(rooms[integer]);
                pq.offer(rooms[integer]);
            });
        }
        System.out.println(res);
    }
}
