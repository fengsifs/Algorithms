package java8;

import java.util.stream.Stream;

/**
 * Created by FengSi on 2016/09/21 at 13:54.
 */
public class IterateTest {
    public static void main(String[] args) {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}
