import java.util.Scanner;

public class Main {
    public static void main(String[] agrs) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            double[] probability = new double[n + 1];
            double[] rightProbability = new double[n + 1];
            rightProbability[0] = 1.0;
            for (int i = 1; i <= n; i++) {
                probability[i] = scanner.nextInt() / 100.0;
                double[] temp = new double[i + 1];
                temp[0] = rightProbability[0] * (1 - probability[i]);
                for (int j = 1; j <= i; j++) {
                    temp[j] = rightProbability[j - 1] * probability[i] + rightProbability[j] * (1 - probability[i]);
                }
                System.arraycopy(temp, 0, rightProbability, 0, temp.length);
            }
            int nums = (int) Math.round(n * 0.6 + 0.4);
            double res = 0;
            for (int i = nums; i < rightProbability.length; i++) {
                res += rightProbability[i];
            }
            res = Math.round(res*100000) / 100000.0;
            System.out.printf("%.5f", res);
        }
    }
}