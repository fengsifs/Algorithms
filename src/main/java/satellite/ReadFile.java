package satellite;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

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
        Matrix m = new Matrix(a);
        SingularValueDecomposition svd = m.svd();
        Matrix u = svd.getU();
        System.out.println("u: " + u.getColumnDimension() + " x " + u.getRowDimension());
//        Matrix s = svd.getS();
//        System.out.println("u: " + s.getColumnDimension() + " x " + s.getRowDimension());
//        Matrix v = svd.getV();
//        System.out.println("v: " + v.getColumnDimension() + " x " + v.getRowDimension());

    }

    public static List<String> timeList(String path) {
        List<String> time = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fs(path)[0]));
            String line = null;
            while ((line = bfr.readLine()) != null)
                time.add(line.split(",")[0]);
            bfr.close();
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
            int size = Integer.MAX_VALUE;
            for (File f : fs) {
                bfr = new BufferedReader(new FileReader(f));
                List<Double> temp = new ArrayList<>();
                String s = null;
                while ((s = bfr.readLine()) != null)
                    temp.add(Double.parseDouble(s.split(",")[1]));
                points[k] = new double[temp.size()];
                int i = 0;
                if (temp.size() < size)
                    size = temp.size();
                for (Double aDouble : temp)
                    points[k][i++] = aDouble;
                k++;
                bfr.close();
            }
            double[][] tempP = new double[fs.length][size];
            for (int i = 0; i < tempP.length; i++)
                for (int j = 0; j < tempP[0].length; j++)
                    tempP[i][j] = points[i][j];
            points = tempP;
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
        List<String> points = new ArrayList<>();
        try {
            BufferedReader bfr;
            File[] fs = fs(prefix);
            int count = 0;
            for (File f : fs) {
                bfr = new BufferedReader(new FileReader(f));
                String line = null;
                while ((line = bfr.readLine()) != null) {
                    String[] temp = line.split(",");
                    time.add(temp[0]);
                    points.add(temp[1]);
                }
                bfr.close();
            }
            System.out.println(time.get(time.size()-1));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(prefix + ".txt")));
            for (int i = 0; i < time.size(); i++) {
                bfw.write(time.get(i) + "," + points.get(i));
                bfw.newLine();
            }
            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
