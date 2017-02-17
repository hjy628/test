package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * 流转换为其他数据结构 (一个Stream只可以使用一次，否则会报错)
 */
public class StreamExchange {

    public static void main(String[] args) {
        Stream stream = Stream.of("a1", "b1", "c1");
        // 1. Array
        String[] strArray1 = (String[]) stream.toArray(String[]::new);
        for (String s : strArray1) { System.out.print(s); } //a1b1c1
        // 2.Collection list
        stream = Stream.of("a1", "b1", "c1");// stream has already been operated upon or closed
        List<String> list1 = (List<String>) stream.collect(Collectors.toList());
        for (String s : list1) { System.out.print(s); }//a1b1c1
        // 2.Collection list
        stream = Stream.of("a1", "b1", "c1");// stream has already been operated upon or closed
        List<String> list2 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
        for (String s : list2) { System.out.print(s); } //a1b1c1
        // 2.Collection set
        stream = Stream.of("a1", "b1", "c1");// stream has already been operated upon or closed
        Set<String> set = (Set<String>) stream.collect(Collectors.toSet());
        for (String s : set) { System.out.print(s); } //a1c1b1
        // 2.Collection stack
        stream = Stream.of("a1", "b1", "c1");// stream has already been operated upon or closed
        Stack<String> stack = (Stack<String>) stream.collect(Collectors.toCollection(Stack::new));
        for (String s : stack) { System.out.print(s); } //a1b1c1
        // 3. String
        stream = Stream.of("a1", "b1", "c1");// stream has already been operated upon or closed
        String str = stream.collect(Collectors.joining()).toString();
        System.out.print(str); // a1b1c1
    }

}
