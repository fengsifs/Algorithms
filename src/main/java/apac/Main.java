package apac;

import java.io.*;
import java.util.*;

/**
 * Created by FengSi on 2016/08/25 at 12:43.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(new File("resource/B-small-attempt0.in")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("resource/RBS.out")));
            int k = 0;
            int t = scanner.nextInt();
            while (k++ < t) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int n = scanner.nextInt();
                int j = scanner.nextInt();
                long c = 0;
                int i = 0;
                while (i++ < n) {
                    long temp = (long) Math.pow(i, a);
                    for (int l = 1; l <= n; l++) {
                        long temp1 = (long) Math.pow(l, b);
                        if (i != l && (temp + temp1) % j == 0) {
                            c++;
                            if (c > 1000000007)
                                c -= 1000000007;
                        }
                    }
                }
                bfw.write("Case #" + k + ": " + c);
                bfw.newLine();
            }
            scanner.close();
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
