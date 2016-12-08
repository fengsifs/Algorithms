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
    public static void write(String path, File f, double[] anomalies, List<String[]> lines, double[] data) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(path + f.getName()));
            System.out.println("id : " + f.getName() + "   total : " + anomalies.length +
                    "   and anomalies : " + Arrays.stream(anomalies).filter(tt -> tt == 1).count());
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
