package stream;

import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * 生产一个等差数列
 */
public class Sequence {

    public static void main(String[] args) {
        Stream.iterate(0, n -> n + 3)
                .limit(10).forEach(x -> System.out.print(x + " "));// 0 3 6 9 12 15 18 21 24 27
        Stream.iterate(4, n -> n + 3)
                .limit(10).forEach(x -> System.out.print(x + " "));// 4 7 10 13 16 19 22 25 28 31
    }

}
