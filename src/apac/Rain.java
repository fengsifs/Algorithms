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
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        boolean[][] visited = new boolean[h.length][h[0].length];
        for (int i = 1; i < h.length - 1; i++) {
            pq.add(h[i][0]);
            map.put(h[i][0], (map.containsKey(h[i][0]) ? map.get(h[i][0]) + 1 : 1));
            pq.add(h[i][h[0].length - 1]);
            map.put(h[i][h[0].length - 1], (map.containsKey(h[i][h[0].length - 1]) ? map.get(h[i][h[0].length - 1]) + 1 : 1));
        }
        for (int j = 1; j < h[0].length - 1; j++) {
            pq.add(h[0][j]);
            map.put(h[0][j], (map.containsKey(h[0][j]) ? map.get(h[0][j]) + 1 : 1));
            pq.add(h[h.length - 1][j]);
            map.put(h[h.length - 1][j], (map.containsKey(h[h.length - 1][j]) ? map.get(h[h.length - 1][j]) + 1 : 1));
        }
        int total = (h.length - 2) * 2 + (h[0].length - 2) * 2;
        while (total-- > 0) {
            pq.poll();
        }
        return res;
    }


}
