package satellite;

import java.util.Arrays;

/**
 * Created by FengSi on 2016/12/10 at 11:47.
 */
public class SpearmanRank {
    public static double spearmanRank(double[] x, double[] y) {
        // 计算斯皮尔曼相关系数
        assert x.length == y.length;
        double avgX = Arrays.stream(x).reduce(0, Double::sum) / x.length;
        double avgY = Arrays.stream(y).reduce(0, Double::sum) / y.length;
        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;
        for (int i = 0; i < Math.min(x.length, y.length); i++) {
            sum1 += (x[i] - avgX) * (y[i] - avgY);
            sum2 += (x[i] - avgX) * (x[i] - avgX);
            sum3 += (y[i] - avgY) * (y[i] - avgY);
        }
        return sum1 / Math.sqrt(sum2 * sum3);
    }
}
