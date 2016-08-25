package apac;

import java.io.*;
import java.util.*;

/**
 * Created by FengSi on 2016/08/25 at 12:43.
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File("resource/B-large-practice.in")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("resource/BL.out")));
            String line = bfr.readLine();
            int lines = Integer.parseInt(line);
            int k = 1;
            while (k <= lines) {
                line = bfr.readLine();
                String[] s = line.split(" ");
                int n = Integer.parseInt(s[0]);
                int l = Integer.parseInt(s[1]);
                int[][] nums = new int[4][n];
                int a = 0;
                while (a < 4) {
                    line = bfr.readLine();
                    s = line.split(" ");
                    for (int i = 0; i < s.length; i++)
                        nums[a][i] = Integer.parseInt(s[i]);
                    a++;
                }
                bfw.write("Case #" + k + ": " + new RobotRockBand().count(nums, l));
                bfw.newLine();
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
