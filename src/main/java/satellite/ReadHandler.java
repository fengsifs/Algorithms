package satellite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by FengSi on 2016/12/08 at 18:37.
 */
public class ReadHandler {
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
