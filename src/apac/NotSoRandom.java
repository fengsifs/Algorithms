package apac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by FengSi on 2016/08/25 at 13:31.
 */
public class NotSoRandom {
    public double expect (String s) {
        String[] ss = s.split(" ");
        int n = Integer.parseInt(ss[0]);
        int x = Integer.parseInt(ss[1]);
        int k = Integer.parseInt(ss[2]);
        double a = Double.parseDouble(ss[3])/100;
        double b = Double.parseDouble(ss[4])/100;
        double c = Double.parseDouble(ss[5]) / 100;
        Map<Integer, Double> map1 = new HashMap<>();
        Map<Integer, Double> map2 = new HashMap<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(x & k);
        map1.put(x & k, a);
        if (set1.add(x | k))
            map1.put(x | k, b);
        else
            map1.put(x | k, map1.get(x | k) + b);
        if (set1.add(x ^ k))
            map1.put(x ^ k, c);
        else
            map1.put(x ^ k, map1.get(x ^ k) + c);
        while (n-- > 1) {
            if (set2.isEmpty()) {
                for (Integer i : set1) {
                    map2.put(i & k, set2.add(i & k) ? map1.get(i) * a : map2.get(i & k) + map1.get(i) * a);
                    map2.put(i | k, set2.add(i | k) ? map1.get(i) * b : map2.get(i | k) + map1.get(i) * b);
                    map2.put(i ^ k, set2.add(i ^ k) ? map1.get(i) * c : map2.get(i ^ k) + map1.get(i) * c);
                }
                set1.clear();
            }
            else {
                for (Integer i : set2) {
                    map1.put(i & k, set1.add(i & k) ? map2.get(i) * a : map1.get(i & k) + map2.get(i) * a);
                    map1.put(i | k, set1.add(i | k) ? map2.get(i) * b : map1.get(i | k) + map2.get(i) * b);
                    map1.put(i ^ k, set1.add(i ^ k) ? map2.get(i) * c : map1.get(i ^ k) + map2.get(i) * c);
                }
                set2.clear();
            }
        }
        if (set1.isEmpty()) {
            double res = 0;
            for (Integer i :
                    set2) {
                res += i * map2.get(i);
            }
            return res;
        }
        else {
            double res = 0;
            for (Integer i :
                    set1) {
                res += i * map1.get(i);
            }
            return res;
        }
    }
}
