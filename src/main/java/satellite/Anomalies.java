package satellite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Stream;

/**
 * Created by FengSi on 2016/11/14 at 14:05.
 */
public class Anomalies {
    public static void main(String[] args) {
        String path = "D:\\Data\\HJ010C\\satellite";
        double[][] a = ReadFile.points(path);
        int i = 1087;
        int j = 0;
        try {
            BufferedWriter bfw;
            while (j < a.length) {
                String output = path + "\\" + i + "-timePointFeature.txt";
                bfw = new BufferedWriter(new FileWriter(new File(output)));
                Complex[][] temp = features(transform(a[j]), 8);
                for (Complex[] aTemp : temp) {
                    for (int k = 0; k < aTemp.length; k++) {
                        if (k < aTemp.length - 1)
                            bfw.write(aTemp[k].re() + "," + aTemp[k].im() + "|");
                        else
                            bfw.write(aTemp[k].re() + "," + aTemp[k].im());
                    }
                    bfw.newLine();
                }
                bfw.close();
                j++;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Complex[][] features(Complex[] data, int p) {

//        // 计算整条时间序列的特征向量
//        int len = 2;
//        while (len < data.length)
//            len *= 2;
//        len /= 2;
//        Complex[] bench = new Complex[len];
//        System.arraycopy(data, 0, bench, 0, len);
//        Complex[] totalFeature = FFT.fft(bench);
//        return totalFeature;

        // 计算每个时间点起始2的p次方时间单位的特征向量
        int testLen = (int) Math.pow(2, p);
        Complex[][] features = new Complex[data.length - testLen][];
        Complex[] temp = new Complex[testLen];
        for (int i = 0; i < features.length; i++) {
            System.arraycopy(data, i, temp, 0, testLen);
            features[i] = FFT.fft(temp);
        }
        return features;
//
//        // 计算每个时间点起始时间段与整体特征的距离
//        double[] distance = new double[features.length];
//        for (int i = 0; i < distance.length; i++)
//            distance[i] = distance(totalFeature, features[i]);
//        return distance;

    }

    private static double distance(Complex[] bench, Complex[] test) {
        double res = 0;
        for (int i = 0; i < test.length; i++)
            res += bench[i].minus(test[i]).abs();
        return res;
    }

    private static Complex[] transform(double[] data) {
        Complex[] result = new Complex[data.length];
        for (int i = 0; i < data.length; i++)
            result[i] = new Complex(data[i], 0);
        return result;
    }
}
