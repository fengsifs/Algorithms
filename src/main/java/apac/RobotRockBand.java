package apac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by FengSi on 2016/08/25 at 12:45.
 */
public class RobotRockBand {
    public long count (int[][] nums, int k) {
        long res = 0;
        Map<Integer, Long> map = new HashMap<>();
        for (int i : nums[0]) {
            for (int j : nums[1]) {
                map.put(i ^ j, (map.containsKey(i ^ j) ? map.get(i ^ j) + 1 : (long) 1));
            }
        }
        for (int i : nums[2]) {
            for (int j : nums[3]) {
                int x = i ^ j ^ k;
                if (map.containsKey(x)) {
                    res += map.get(x);
                }
            }
        }
        return res;
    }
}
