package satellite;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

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
                double rank = 0;
                if (args[1].equals("1"))
                    rank = new SpearmansCorrelation().correlation(points[i], points[j]);
                else if (args[1].equals("2"))
                    rank = SpearmanRank.spearmanRank(points[i], points[j]);
                System.out.println(fs[i].getName().split("\\.")[0] +
                        "," +
                        fs[j].getName().split("\\.")[0] +
                        "," + rank);
            }
        }
    }
}
