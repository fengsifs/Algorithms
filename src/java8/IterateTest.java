package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        Comparator<String> stringComparator = (o1, o2) -> {
            String a = o1 + o2;
            String b = o2 + o1;
            return a.compareTo(b);
        };

        List<String> strings = new ArrayList<>();
        strings.sort(
                (x, y) -> {
                    String xx = x + y;
                    String yy = y + x;
                    return yy.compareTo(xx);
                }
        );
        List<String> strings1 = new ArrayList<>();
        strings.forEach(strings1::add);
    }
}
