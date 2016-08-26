package apac;

import java.io.*;
import java.util.*;

/**
 * Created by FengSi on 2016/08/25 at 12:43.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(new File("resource/C-small-practice.in")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("resource/CS.out")));
            int k = 0;
            int t = scanner.nextInt();
            while (k++ < t) {
                int n = scanner.nextInt();
                double[] nums = new double[n + 1];
                for (int i = 0; i <= n; i++) {
                    nums[i] = scanner.nextDouble();
                }
                nums[0] = - nums[0];
                bfw.write("Case #" + k + ": " + IRR.irr(nums));
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
