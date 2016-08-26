package apac;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by FengSi on 2016/08/25 at 19:19.
 */
public class Rain {
    static int res;

    public static int rain(int[][] h) {
        res = 0;
        if (h.length < 3 || h[0].length < 3)
            return res;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[h.length][h[0].length];
        Point p;
        for (int i = 1; i < h.length - 1; i++) {
            p = new Point(h[i][0], i, 0);
            pq.add(p);
            p = new Point(h[i][h[0].length - 1], i, h[0].length - 1);
            pq.add(p);
        }
        for (int j = 1; j < h[0].length - 1; j++) {
            p = new Point(h[0][j], 0, j);
            pq.add(p);
            p = new Point(h[h.length - 1][j], h.length - 1, j);
            pq.add(p);
        }
        while (!pq.isEmpty()) {
            p = pq.poll();
            visited[p.x][p.y] = true;
            int x, y;
            if (p.x == 0) {
                x = p.x + 1;
                y = p.y;
            } else if (p.x == h.length - 1) {
                x = p.x - 1;
                y = p.y;
            } else if (p.y == 0) {
                x = p.x;
                y = p.y + 1;
            } else {
                x = p.x;
                y = p.y - 1;
            }
            if (h[p.x][p.y] > h[x][y]) {
                res += h[p.x][p.y] - h[x][y];
                h[x][y] = h[p.x][p.y];
                visit(h, visited, x, y);
            }

        }
        return res;
    }

    private static void visit(int[][] h, boolean[][] visited, int x, int y) {
            visited[x][y] = true;
    }

    static class Point implements Comparable<Point> {
        int height;
        int x;
        int y;

        public Point(int height, int x, int y) {
            this.height = height;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point b) {
            return height - b.height;
        }

    }
}
