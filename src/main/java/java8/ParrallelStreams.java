package java8;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by FengSi on 2016/10/25 at 20:01.
 */
public class ParrallelStreams {
    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " +
                measureSumPerf(ParrallelStreams::sequentialSum, 10000000) + " msecs");
        System.out.println("Iterative sum done in: " +
                measureSumPerf(ParrallelStreams::iterativeSum, 10000000) + " msecs");
        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParrallelStreams::parallelSum, 10000000) + " msecs");
        System.out.println("Range sum done in: " +
                measureSumPerf(ParrallelStreams::rangeSum, 10000000) + " msecs");
        System.out.println("Parallel range sum done in: " +
                measureSumPerf(ParrallelStreams::parallelRangeSum, 10000000) + " msecs");
    }

    private static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    private static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    private static long iterativeSum(long n) {
        long res = 0;
        for (long i = 1L; i <= n; i++)
            res += i;
        return res;
    }

    private static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    private static long rangeSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    private static long parallelRangeSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
