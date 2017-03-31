package course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengSi on 2017/03/06 at 12:34.
 */
public class Prime {
    public static void main(String[] args) {
        System.out.println("All prime numbers between 2 and 100 are:\n" + findPrime(100));
        System.out.println("All prime numbers between 2 and 200 are:\n " + findPrime(200));
    }

    private static List<Integer> findPrime(int n) {
        List<Integer> list = new ArrayList<>();
        boolean[] marked = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!marked[i]) {
                list.add(i);
                for (int j = i * i; j <= n; j += i) {
                    marked[j] = true;
                }
            }
        }
        return list;
    }
}

/*
All prime numbers between 2 and 100 are:
[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
All prime numbers between 2 and 200 are:
[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199]
 */