package satellite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by FengSi on 2016/12/15 at 10:32.
 */
public class CalRatio {
    public static void main(String[] args) {
        try {
            File root = new File(args[0]);
            File[] fs = root.listFiles();
            BufferedReader reader;
            assert fs != null;
            for (File f : fs) {
                reader = new BufferedReader(new FileReader(f));
                String line;
                double anom = 0;
                double total = 0;
                while ((line = reader.readLine()) != null) {
                    total++;
                    if (line.split(",")[2].equals("1.0"))
                        anom++;
                }
                System.out.println("id : " + f.getName().split("\\.")[0] + "   total : " + total +
                        "   and anomalies : " + anom);
                System.out.println("the anomalies ratio should be : " + WriteHandler.RATE);
                System.out.println("and the detected ratio result : " + (anom / total));
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
