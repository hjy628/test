package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * 转换大写 【.map(String::toUpperCase)】和【map(s -> { return s.toUpperCase(); })】和 【.map(s -> s.toUpperCase())】
 */
public class ToUpperCase {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "java8", "stream");
        List<String> wordList = stream.map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(wordList.toString());// [HELLO, WORLD, JAVA8, STREAM]

        stream = Stream.of("hello", "world", "java8", "stream");
        wordList = stream.map(s -> { return s.toUpperCase(); }).collect(Collectors.toList());
        System.out.println(wordList.toString());// [HELLO, WORLD, JAVA8, STREAM]

        stream = Stream.of("hello", "world", "java8", "stream");
        wordList = stream.map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(wordList.toString());// [HELLO, WORLD, JAVA8, STREAM]
    }

}
