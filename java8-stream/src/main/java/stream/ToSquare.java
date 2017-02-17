package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * 平方数 (map 生产的是个1:1的映射，每个输入元素，都按照规则转换成另一个元素)
 */
public class ToSquare {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4).stream();
        List<Integer> squareList = stream.map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareList.toString());// [1, 4, 9, 16]
    }

}
