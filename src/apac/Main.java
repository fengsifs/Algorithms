package apac;

import java.io.*;
import java.util.*;

/**
 * Created by FengSi on 2016/08/25 at 12:43.
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File("resource/D-large-practice.in")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("resource/DL.out")));
            String line = bfr.readLine();
            int lines = Integer.parseInt(line);
            int k = 1;
            while (lines-- > 0) {
                line = bfr.readLine();
                String[] s = line.split(" ");
                int n = Integer.parseInt(s[0]);
                int q = Integer.parseInt(s[1]);
                line = bfr.readLine();
                s = line.split(" ");
                int[] nums = new int[s.length];
                long[] sum = new long[nums.length];
                for (int i = 0; i < s.length; i++) {
                    nums[i] = Integer.parseInt(s[i]);
                    sum[i] = nums[i] + (i > 0 ? sum[i - 1] : 0);
                }
                List<Long> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    for (int j = i; j < n; j++) {
                        list.add(sum[j] - (i > 0 ? sum[i - 1] : 0));
                    }
                }
                Collections.sort(list);
                sum = new long[list.size()];
                for (int i = 0; i < sum.length; i++)
                    sum[i] = list.get(i) + (i > 0 ? sum[i - 1] : 0);
                bfw.write("Case #" + k + ":");
                bfw.newLine();
                for (int i = 0; i < q; i++) {
                    line = bfr.readLine();
                    s = line.split(" ");
                    int x = Integer.parseInt(s[0])-1;
                    int y = Integer.parseInt(s[1])-1;
                    long t = sum[y] - (x > 0 ? sum[x - 1] : 0);
                    bfw.write(t + "");
                    bfw.newLine();
                }
//                String[] s = line.split(" ");
//                int n = Integer.parseInt(s[0]);
//                int l = Integer.parseInt(s[1]);
//                int[][] nums = new int[4][n];
//                int a = 0;
//                while (a < 4) {
//                    line = bfr.readLine();
//                    s = line.split(" ");
//                    for (int i = 0; i < s.length; i++)
//                        nums[a][i] = Integer.parseInt(s[i]);
//                    a++;
//                }
//                bfw.write("Case #" + k + ": " + new NotSoRandom().expect(line));
//                bfw.write("Case #" + k + ": " + new RobotRockBand().count(nums, l));
//                bfw.newLine();
                k++;
            }
            bfr.close();
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
