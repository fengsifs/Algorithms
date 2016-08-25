package apac;

/**
 * Created by FengSi on 2016/08/25 at 12:45.
 */
public class RobotRockBand {
    public int count (int[][] nums, int k) {
        int res = 0;
        for (int a = 0; a < nums[0].length; a++) {
            for (int b = 0; b < nums[0].length; b++) {
                for (int c = 0; c < nums[0].length; c++) {
                    for (int d = 0; d < nums[0].length; d++) {
                        if ((nums[0][a]^nums[1][b]^nums[2][c]^nums[3][d]) == k) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
