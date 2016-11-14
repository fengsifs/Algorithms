package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

/**
 * Created by FengSi on 2016/09/29 at 12:57.
 */
public class Partition {
    public static void main(String[] args) {
        System.out.println(partitionPrimes(1000).get(true).stream()
                .map(number -> Integer.toString(number)).collect(joining(",")));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(Partition::isPrime)
                );
    }
}
