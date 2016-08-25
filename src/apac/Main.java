package apac;

import java.io.*;
import java.util.*;

/**
 * Created by FengSi on 2016/08/25 at 12:43.
 */
public class Main {
    public static void main(String[] args) {
        try {
//            BufferedReader bfr = new BufferedReader(new FileReader(new File("resource/B-large-practice.in")));
            Scanner scanner = new Scanner(new FileInputStream(new File("resource/A-large-practice.in")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("resource/AL.out")));
            int k = 0;
            int t = scanner.nextInt();
            while (k++ < t) {
                int n = scanner.nextInt();
                Map<Integer, PriorityQueue<String>> map = new HashMap<>();
                int max = 0;
                scanner.nextLine();
                while (n-- > 0) {
                    String s = scanner.nextLine();
                    int c = CountString.count(s);
                    if (c > max) {
                        max = c;
                        PriorityQueue<String> pq = new PriorityQueue<>();
                        pq.offer(s);
                        map.put(c, pq);
                    } else if (c == max) {
                        map.get(c).offer(s);
                    }
                }
                bfw.write("Case #" + k + ": " + map.get(max).peek());
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
