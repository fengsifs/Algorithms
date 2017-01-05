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
    public static final double RATE = (double) 5042 / 1150554;

    public static void write(String path, File f, double[] anomalies, List<String[]> lines, double[] data,
                             int p, int feature, double threshold, Complex[] totalFeature) {
        try {
            File file = new File(path + f.getName().split("\\.")[0]
                    + "_anomalies_" + p + "_" + feature + "_" + threshold + ".csv");
            long a = anomalies.length;
            long b = Arrays.stream(anomalies).filter(tt -> tt == 1).count();
            System.out.println("id : " + f.getName().split("\\.")[0] + "   total : " + a +
                    "   and anomalies : " + b);
            System.out.println("the anomalies ratio should be : " + RATE);
            System.out.println("and the detected ratio result : " + ((double) b / a));
            BufferedWriter bfw;
            if (!file.exists()) {
                bfw = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < anomalies.length; i++) {
                    bfw.write(lines.get(i)[0] + "," + data[i] + "," + anomalies[i]);
                    bfw.newLine();
                }
                bfw.close();
            }
            file = new File(path + f.getName().split("\\.")[0]
                    + "_totalFeature.csv");
            if (!file.exists()) {
                bfw = new BufferedWriter(new FileWriter(file));
                for (Complex complex : totalFeature) {
                    bfw.write(complex.re() + "," + complex.im());
                    bfw.newLine();
                }
                bfw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String path, File f, double[] anomalies,
                             List<String[]> lines, double[] data, String method) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(path + f.getName().split("\\.")[0]
                    + "_anomalies_" + method + ".csv"));
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
