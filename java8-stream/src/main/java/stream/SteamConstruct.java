package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 */
public class SteamConstruct {

    public static void main(String[] args) {
        // 1. Individual values 单独值
        Stream stream = Stream.of("a1", "b1", "c1");
        stream.forEach(System.out::print);//打印 a1b1c1

        // 2. Arrays 数组
        String[] strArray = new String[] {"a2", "b2", "c2"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        System.out.println(stream.collect(Collectors.joining(",")).toString());//打印 a2,b2,c2

        // 3. Collections 集合
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
    }

}
