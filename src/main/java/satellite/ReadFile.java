package satellite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengSi on 2016/11/10 at 13:17.
 */
public class ReadFile {
    public static void main(String[] args) {
        String path = "D:\\Data\\HJ010C\\satellite";
        double[][] a = points(path);
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static List<String> timeList(String path) {
        List<String> time = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fs(path)[0]));
            String line = null;
            while ((line = bfr.readLine()) != null)
                time.add(line.split(",")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static double[][] points(String path) {
        File[] fs = fs(path);
        double[][] points = new double[fs.length][];
        try {
            BufferedReader bfr;
            int k = 0;
            for (File f : fs) {
                bfr = new BufferedReader(new FileReader(f));
                List<Double> temp = new ArrayList<>();
                String s = null;
                while ((s = bfr.readLine()) != null)
                    temp.add(Double.parseDouble(s.split(",")[1]));
                points[k] = new double[temp.size()];
                int i = 0;
                for (Double aDouble : temp)
                    points[k][i++] = aDouble;
                k++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }

    private static File[] fs(String path) {
        File root = new File(path);
        return root.listFiles();
    }

    private static void writeFile(String prefix) {
        List<String> time = new ArrayList<>();
        List<Double> points = new ArrayList<>();
        try {
            BufferedReader bfr;
            File[] fs = fs(prefix);
            for (File f : fs) {
                bfr = new BufferedReader(new FileReader(f));
                String line = null;
                while ((line = bfr.readLine()) != null) {
                    String[] temp = line.split(",");
                    time.add(temp[0]);
                    points.add(Double.parseDouble(temp[1]));
                }
            }
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(prefix + ".txt")));
            for (int i = 0; i < time.size(); i++) {
                bfw.write(time.get(i) + "," + points.get(i));
                bfw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
