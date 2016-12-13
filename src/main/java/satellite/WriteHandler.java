package satellite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FengSi on 2016/12/08 at 18:30.
 */
public class WriteHandler {
    private static final double RATE = (double) 5042 / 1150554;

    public static void write(String path, File f, double[] anomalies, List<String[]> lines, double[] data) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(path + f.getName()));
            long a = anomalies.length;
            long b = Arrays.stream(anomalies).filter(tt -> tt == 1).count();
            System.out.println("id : " + f.getName().split("\\.")[0] + "   total : " + a +
                    "   and anomalies : " + b);
            System.out.println("the anomalies ratio should be : " + RATE);
            System.out.println("and the detected ratio result : " + ((double) b / a));
            for (int i = 0; i < anomalies.length; i++) {
                bfw.write(lines.get(i)[0] + "," + data[i] + "," + anomalies[i]);
                bfw.newLine();
            }
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
