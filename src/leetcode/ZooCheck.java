package leetcode;

import leetcode.backTracking.GenerateParentheses;
import leetcode.contests.weeklyContest_6.ConvertaNumbertoHexadecimal;
import leetcode.contests.weeklyContest_6.TrappingRainWaterII;

import java.util.PriorityQueue;

/**
 * Created by FengSi on 2016/08/28 at 9:22.
 */
public class ZooCheck {
    public static void main(String[] args) {
        TrappingRainWaterII trappingRainWaterII = new TrappingRainWaterII();
        int[][] ints = {{1,4,3,1,3,2},{3,2,1,1,2,4},{2,3,3,2,3,1}};
        System.out.println(trappingRainWaterII.trapRainWater(ints));
    }
}
