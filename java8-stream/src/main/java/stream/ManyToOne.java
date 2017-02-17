package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * 一对多 (flatMap把input stream中的层级结构扁平化，就是将底层元素抽出来放到一起，最终output的Stream里面已经没有List了，都是直接的数字)
 */
public class ManyToOne {

    public static void main(String[] args) {
        Stream<List<Integer>> inputStream =
                Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream());
        System.out.print(outputStream.collect(Collectors.toList()).toString());// [1, 2, 3, 4, 5, 6]
    }

}
