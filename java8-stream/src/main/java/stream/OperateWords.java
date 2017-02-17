package stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * 找出全文的单词，转小写，并且排序
 */
public class OperateWords {

    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.dir")
                + "/java8-stream/src/main/java/stream/OperateWords.java");
        // 2. BufferedReader + Stream
        try (BufferedReader br = Files.newBufferedReader(path)) {
            List<String> output = br.lines()
                    .flatMap(line -> Stream.of(line.split(" ")))
                    .map(String::toLowerCase)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
