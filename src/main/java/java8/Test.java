package java8;

/**
 * Created by FengSi on 2016/11/01 at 15:56.
 */
public class Test {
    public static void main(String[] args) {
        
    }

    private static double average(double a, double... values) {
        double res = a;
        for (double value : values) {
            res += value;
        }
        return res / (values.length + 1);
    }
}