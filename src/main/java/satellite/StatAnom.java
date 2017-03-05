package satellite;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FengSi on 2016/12/08 at 18:41.
 */
public class StatAnom {
    public static void main(String[] args) {
//        File[] fs = ReadFile.fs(args[0]);
//        for (File f : fs) {
//            List<String[]> lines = ReadHandler.lines(f);
//            double[] data = ReadHandler.data(lines);
//            double avg = Arrays.stream(data).reduce(0, Double::sum) / data.length;
//            double std = Math.sqrt(Arrays.stream(data).map(i -> (i - avg) * (i - avg)).
//                    reduce(0, Double::sum) / data.length);
//            double[] anomalies = Arrays.stream(data).map(i -> (i - avg) / std)
//                    .map(i -> Math.abs(i) > Double.parseDouble(args[2]) ? 1 : 0)
//                    .toArray();
//            WriteHandler.write(args[1], f, anomalies, lines, data, "stat");
//        }
//        int[][] a = {{1, 2}, {2, 2}};
//        int[][] b = {{3, 4}, {4, 5}};
//        int[][][] c = {a, b};
        System.out.println(System.getProperty("thrift.port", "6942"));
    }

    public static boolean isPortOccupied(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }
}
