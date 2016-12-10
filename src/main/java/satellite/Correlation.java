package satellite;

import java.io.File;

/**
 * Created by FengSi on 2016/12/10 at 11:48.
 */
public class Correlation {
    public static void main(String[] args) {
        String path = args[0];
        File[] fs = ReadFile.fs(path);
        double[][] points = ReadFile.points(args[0]);
        for (int i = 0; i < fs.length; i++) {
            for (int j = i + 1; j < fs.length; j++) {
                System.out.println(fs[i].getName().split("\\.")[0] +
                    "'s correlation with " +
                    fs[j].getName().split("\\.")[0] +
                    " is: " +
                    SpearmanRank.spearmanRank(points[i], points[j]));
            }
        }
    }
}
