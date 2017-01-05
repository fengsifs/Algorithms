package leetcode.binarySearch;

import java.util.Arrays;

/**
 * Created by FengSi on 2017/01/05 at 23:41.
 */
public class Heaters {
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        int j = 0;
        int dis;
        while (j < heaters.length - 1 && i < houses.length) {
            while (j < heaters.length - 1 && heaters[j+1] == heaters[j])
                j++;
            dis = Math.abs(houses[i] - heaters[j]);
            if (dis <= radius)
                i++;
            else {
                int t = Math.abs(houses[i] - heaters[j+1]);
                if (dis < t) {
                    radius = dis;
                    i++;
                }
                else if (dis == t) {
                    radius = dis;
                    i++;
                    j++;
                }
                else
                    j++;
            }
        }
        while (i < houses.length) {
            dis = Math.abs(houses[i] - heaters[j]);
            if (dis > radius)
                radius = dis;
            i++;
        }
        return radius;
    }
}
