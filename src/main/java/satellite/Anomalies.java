package satellite;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static satellite.FFT.fft;

/**
 * Created by FengSi on 2016/11/14 at 14:05.
 */
public class Anomalies {
    public static void main(String[] args) {
        String path = args[0];
        File[] fs = ReadFile.fs(path);
        for (File f : fs) {
            List<String[]> lines = ReadHandler.lines(f);
            double[] data = ReadHandler.data(lines);
            Complex[] complexes = transform(data);
            double[] anomalies = findAnomalies(standardization
                    (distances(totalFeature(complexes), features(complexes, 5))), Double.parseDouble(args[2]));
            WriteHandler.write(args[1], f, anomalies, lines, data);
        }
    }

    private static Complex[] totalFeature(Complex[] data) {
        // 计算整条时间序列的特征向量
        int len = 2;
        while (len < data.length)
            len *= 2;
        len /= 2;
        Complex[] bench = new Complex[len];
        System.arraycopy(data, 0, bench, 0, len);
        return fft(bench);
    }

    private static Complex[][] features(Complex[] data, int p) {
        // 计算每个时间点起始2的p次方时间单位的特征向量
        int testLen = (int) Math.pow(2, p);
        Complex[][] features = new Complex[data.length - testLen][];
        Complex[] temp = new Complex[testLen];
        for (int i = 0; i < features.length; i++) {
            System.arraycopy(data, i, temp, 0, testLen);
            features[i] = fft(temp);
        }
        return features;
    }

    private static double[] distances(Complex[] totalFeature, Complex[][] features) {
        // 计算每个时间点起始时间段与整体特征的距离
        double[] distances = new double[features.length];
        for (int i = 0; i < distances.length; i++)
            distances[i] = distance(totalFeature, features[i]);
        return distances;
    }


    private static double distance(Complex[] bench, Complex[] test) {
        // 计算测试数组与基准数组的距离
        double res = 0;
        for (int i = 0; i < test.length; i++)
            res += bench[i].minus(test[i]).abs();
        return res;
    }

    private static Complex[] transform(double[] data) {
        // 将double数组转换为Complex数组
        Complex[] result = new Complex[data.length];
        for (int i = 0; i < data.length; i++)
            result[i] = new Complex(data[i], 0);
        return result;
    }

    public static double[] standardization(double[] data) {
        // 将计算得到的距离数据标准化处理
        double avg = Arrays.stream(data).reduce(0, Double::sum) / data.length;
        double std = Math.sqrt(Arrays.stream(data).map(i -> (i - avg) * (i - avg)).
                reduce(0, Double::sum) / data.length);
        return Arrays.stream(data).map(i -> (i - avg) / std).toArray();
    }

    public static double[] findAnomalies(double[] data, double threshold) {
        // 经标准化处理后数据根据设定的阈值寻找异常点
        return Arrays.stream(data).map(i -> Math.abs(i) > threshold ? 1 : 0).toArray();
    }
}
