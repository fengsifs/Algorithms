package satellite;

import java.io.File;
import java.util.List;

/**
 * Created by FengSi on 2016/12/08 at 18:41.
 */
public class StatAnom {
    public static void main(String[] args) {
        File[] fs = ReadFile.fs(args[0]);
        for (File f : fs) {
            List<String[]> lines = ReadHandler.lines(f);
            double[] data = ReadHandler.data(lines);
            double[] anomalies = Anomalies.findAnomalies(Anomalies.standardization(data),
                    Double.parseDouble(args[2]));
            WriteHandler.write(args[1], f, anomalies, lines, data);
        }
    }
}
