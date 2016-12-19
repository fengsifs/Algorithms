package satellite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by FengSi on 2016/12/08 at 18:37.
 */
public class ReadHandler {
    public static void main(String[] args) {
        File f = new File(args[0]);
        List<String[]> lines = lines(f);
        double[] data = data(lines);
        double[] anomalies = Arrays.stream(data).mapToInt(d -> (int) d).map(i -> {
            String s = Integer.toHexString(i);
            char c = s.charAt(0);
            if (c == '2' || c == '4' || c == '5' || c == '6' || c == '7')
                return 0;
            else
                return 1;
        }).mapToDouble(d -> (double) d).toArray();
        WriteHandler.write(args[1], f, anomalies, lines, data, "benchmark");
    }

    public static double[] data(List<String[]> lines) {
        double[] data = new double[lines.size()];
        for (int i = 0; i < data.length; i++)
            data[i] = Double.parseDouble(lines.get(i)[1]);
        return data;
    }

    public static List<String[]> lines(File f) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            List<String[]> lines = bfr.lines().map(s -> s.split(",")).collect(Collectors.toList());
            bfr.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
