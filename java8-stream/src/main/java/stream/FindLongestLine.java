package stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by hjy on 17-2-17.
 * 找出最长一行的长度
 */
public class FindLongestLine {

    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.dir") + "/java8-stream/src/main/java/stream/FindLongestLine.java");
        // 2. BufferedReader + Stream
        try (BufferedReader br = Files.newBufferedReader(path)) {
            int output = br.lines()
                    .mapToInt(String::length)
                    .max()
                    .getAsInt();
            System.out.println(output);// 83
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
