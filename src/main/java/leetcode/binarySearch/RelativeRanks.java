package leetcode.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by FengSi on 2017/02/06 at 14:45.
 */
public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        int[] ranks = Arrays.stream(nums)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i)
                .toArray();
        String[] res = new String[nums.length];
        int k;
        for (int i = 0; i < res.length; i++) {
            k = Arrays.binarySearch(ranks, nums[i]) + 1;
            if (k > 3) {
                res[i] = k + "";
            } else {
                if (k == 1)
                    res[i] = "Gold Medal";
                else if (k == 2)
                    res[i] = "Silver Medal";
                else
                    res[i] = "Bronze Medal";
            }
        }
        return res;
    }
}
