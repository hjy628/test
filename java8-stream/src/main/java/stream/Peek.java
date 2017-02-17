package stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 *  peek 对每个元素执行操作并且返回一个新的Stream 【peek : 偷窥】注意执行顺序
 */
public class Peek {

    public static void main(String[] args) {
        Stream.of("one", "two", "three", "four")
                .filter(p -> p.length() > 3)
                .peek(v -> System.out.println("Filtered Value:" + v))
                .map(String::toUpperCase)
                .peek(v -> System.out.println("Mapped Value:" + v))
                .collect(Collectors.toList());
        // 1. Filtered Value:three
        // 2. Mapped Value:THREE
        // 3. Filtered Value:four
        // 4. Mapped Value:FOUR
    }

}
